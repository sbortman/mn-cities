package com.maxar.domain

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CityRepository extends JpaRepository<City, Long> {

}