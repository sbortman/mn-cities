package com.maxar.service

import io.micronaut.http.MediaType
import io.micronaut.http.server.types.files.StreamedFile
import jakarta.inject.Singleton
import org.apache.commons.io.output.ByteArrayOutputStream

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

@Singleton
class WebMappingService {
  StreamedFile getMap() {
    BufferedImage image = new BufferedImage( 256, 256, BufferedImage.TYPE_INT_ARGB )
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
    Graphics2D g2d = image?.createGraphics()

    g2d.color = Color.red
    g2d.drawRect( 0, 0, image.width, image.height )
    g2d.dispose()

    try {
      ImageIO.write( image, 'png', outputStream )
    } catch ( Exception e ) {
      log.error( e.message )
    }

    new StreamedFile( outputStream?.toInputStream(), MediaType.IMAGE_PNG_TYPE )
  }
}
