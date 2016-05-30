package Configuration;

import lombok.Getter;
import java.lang.String;
import java.util.UUID;

/**
 * Created by Alex on 5/30/16.
 */
public class ConfigurationManager {
    private static ConfigurationManager ourInstance = new ConfigurationManager();
    private BigTrackConfiguration bigTrackConfiguration;

    @Getter
    private UUID sessionId = UUID.randomUUID();

    public static ConfigurationManager getInstance() {
        return ourInstance;
    }

    private ConfigurationManager() {
        // todo load conf. from XML and initialize dialect drivers
    }

    private List<DatabaseConfiguration> getDatabaseConfigurations(){
        return bigTrackConfiguration.getDatabaseConfigurations();
    }
}
