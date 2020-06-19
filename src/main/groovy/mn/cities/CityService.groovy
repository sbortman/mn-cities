package mn.cities

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel

import javax.inject.Singleton

@Singleton
class CityService implements ApplicationEventListener<ServerStartupEvent> {
    CityRepository cityRepository

    CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        if (cityRepository.count() == 0) {

            File csvFile = new File('cities.csv')
            GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326)

            csvFile.eachLine(0) { String line, Integer index ->
                if (index > 0) {
                    List<String> tokens = line?.split(',')

                    City city = new City(
                            name: tokens[0],
                            country: tokens[1],
                            population: tokens[2] as Integer,
                            capitol: tokens[3] == 'Y',
                            longitude: tokens[4] as Double,
                            latitude: tokens[5] as Double
                    )

                    city.location = geometryFactory.createPoint(new Coordinate(city.longitude, city.latitude))
                    cityRepository.save(city)

                    println city
                }
            }
        }
    }
}