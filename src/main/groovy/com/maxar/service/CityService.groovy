package com.maxar.service

import com.maxar.domain.City
import com.maxar.domain.CityRepository
import groovy.util.logging.Slf4j
import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import jakarta.inject.Singleton
import org.geotools.geometry.jts.JTSFactoryFinder
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.geotools.referencing.CRS
import org.geotools.api.referencing.crs.CoordinateReferenceSystem

@Slf4j
@Singleton
class CityService {
  CityRepository cityRepository

  CityService( CityRepository cityRepository ) {
    this.cityRepository = cityRepository
  }

  @EventListener
  def onStartUp( StartupEvent event ) {

    if ( cityRepository.count() == 0 ) {
      File csvFile = new File( 'data/cities.csv' )

      csvFile?.eachLine { String line ->
        List<String> tokens = line?.split( ',' ) as List<String>

        City city = new City(
            name: tokens[ 0 ],
            country: tokens[ 1 ],
            population: tokens[ 2 ]?.toInteger(),
            capital: tokens[ 3 ] == 'Y',
            longitude: tokens[ 4 ]?.toDouble(),
            latitude: tokens[ 5 ]?.toDouble()
        )

        city.location = createPoint( city.latitude, city.longitude )
        cityRepository.save( city )

        log.info( "${ city }" )

      }
    }

    log.info( "Cities count: ${ cityRepository.count() }" )
  }

  static Point createPoint( Double latitude, Double longitude) {
    // Create a GeometryFactory
    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory()

    // Create the point
    Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude))

    // Define the EPSG:4326 coordinate reference system
    CoordinateReferenceSystem crs = CRS.decode("EPSG:4326")

    // Print the CRS information (optional)
    println("CRS: ${crs}")

    return point
  }
}
