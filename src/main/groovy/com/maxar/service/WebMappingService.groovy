package com.maxar.service

import com.maxar.controller.GetMapRequest

import geoscript.geom.Bounds
import geoscript.layer.Shapefile
import geoscript.proj.Projection
import static geoscript.style.Symbolizers.*

import groovy.util.logging.Slf4j

import io.micronaut.context.event.StartupEvent
import io.micronaut.http.MediaType
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.runtime.event.annotation.EventListener

import jakarta.inject.Singleton

import org.apache.commons.io.output.ByteArrayOutputStream
import org.geotools.referencing.util.CRSUtilities

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

import geoscript.render.Map as GeoScriptMap

@Slf4j
@Singleton
class WebMappingService {
  Shapefile countries
  Shapefile states

  StreamedFile getMap( GetMapRequest getMapRequest ) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()

    switch ( getMapRequest?.layers?.toUpperCase() ) {
    case 'TILE_GRID':
      BufferedImage image = new BufferedImage( getMapRequest?.width, getMapRequest?.width, BufferedImage.TYPE_INT_ARGB )
      Graphics2D g2d = image?.createGraphics()

      g2d.color = Color.red
      g2d.drawRect( 0, 0, image.width, image.height )
      g2d.dispose()

      try {
        ImageIO.write( image, 'png', outputStream )
      } catch ( Exception e ) {
        log.error( e.message )
      }
      break
    case 'BORDERS':
      def coords = getMapRequest?.bbox?.split( ',' )?.collect { it?.toDouble() } as List<Double>
      def proj = new Projection( getMapRequest?.srs )
      def bounds
      def units = CRSUtilities.getUnit( proj?.crs?.coordinateSystem )?.toString()

      if ( getMapRequest?.version == '1.3.0' && units == 'deg' ) {
        bounds = new Bounds( coords[ 1 ], coords[ 0 ], coords[ 3 ], coords[ 2 ], proj )
      } else {
        bounds = new Bounds( coords[ 0 ], coords[ 1 ], coords[ 2 ], coords[ 3 ], proj )
      }

      def map = new GeoScriptMap(
          fixAspectRatio: false,
          width: getMapRequest?.width,
          height: getMapRequest?.height,
          type: 'png',
          bounds: bounds,
          proj: bounds?.proj,
          layers: [
              countries,
              states
          ]
      )

      map?.render( outputStream )
      map?.close()
      break
    }

    new StreamedFile( outputStream?.toInputStream(), MediaType.IMAGE_PNG_TYPE )
  }

  @EventListener
  def onStartUp( StartupEvent event ) {
    def style = stroke( color: 'yellow' ) + fill( opacity: 0 )

    countries = new Shapefile( 'data/world_adm0.shp' )
    countries?.style = style

    states = new Shapefile( 'data/statesp020.shp' )
    states?.style = style
  }
}
