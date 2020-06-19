package mn.cities

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.io.WKTWriter

class WktGeometrySerializer extends StdSerializer<Geometry> {
    WKTWriter wktWriter = new WKTWriter()

    WktGeometrySerializer() {
        this(null)
    }

    WktGeometrySerializer(Class t) {
        super(t)
    }


    @Override
    void serialize(Geometry value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(wktWriter.write(value))
    }
}
