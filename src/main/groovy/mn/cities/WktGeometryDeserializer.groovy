package mn.cities

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel
import org.locationtech.jts.io.WKTReader

class WktGeometryDeserializer extends StdDeserializer<Geometry> {
    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), 4326)
    WKTReader wktReader = new WKTReader(geometryFactory)

    WktGeometryDeserializer() {
        this(null)
    }

    WktGeometryDeserializer(Class t) {
        super(t)
    }

    @Override
    Geometry deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String s = p.getValueAsString('')
        println "s=${s}"
        return wktReader.read(s)
    }
}
