package mn.cities

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface CityRepository extends CrudRepository<City, Long> {
}
