package mn.cities


import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import groovy.transform.ToString
import org.locationtech.jts.geom.Point

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@ToString(includeNames = true)
@Entity
@Table(name = 'cities')
class City {
    @GeneratedValue
    @Id
    Long id
    String name
    String country
    Integer population
    Boolean capitol
    Double longitude
    Double latitude

    @JsonSerialize(using = WktGeometrySerializer)
    @JsonDeserialize(using = WktGeometryDeserializer)
    @Column(columnDefinition = 'geometry(Point, 4326)')
    Point location
}
