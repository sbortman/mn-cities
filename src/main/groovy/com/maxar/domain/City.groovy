package com.maxar.domain

import groovy.transform.ToString
import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.locationtech.jts.geom.Point

@Serdeable
@Entity
@ToString(includeNames = true)
class City {
  @Id
  @GeneratedValue
  Long id

  String name
  String country
  Integer population
  Boolean capital
  Double longitude
  Double latitude

  @Column(columnDefinition = "Geometry(Point, 4326)")
  Point location
}
