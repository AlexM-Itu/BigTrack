package Domain;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

/**
 * Created by Alex on 6/11/16.
 */
public class MapperConfiguration {
    public static void Configure (){
        CodecRegistry.DEFAULT_INSTANCE.register(new EnumNameCodec<Operations>(Operations.class));
    }
}
