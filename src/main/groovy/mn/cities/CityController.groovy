package mn.cities

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/city")
class CityController {

    CityRepository cityRepository

    CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository
    }

    @Get("/")
    HttpStatus index() {
        return HttpStatus.OK
    }

    @Get('/list')
    List<City> list() {
        List<City> cities = cityRepository.findAll()
        cities
    }

    @Get('/{id}')
    City findById(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElse(null)

        println "city: ${city}"
        city
    }

    @Post('/create')
    City create(@Body City city) {
        cityRepository.save(city)
        println city
        city
    }
}