package Configuration;

/**
 * Created by Alex on 5/30/16.
 */
public class ConfigurationManager {
    private static ConfigurationManager ourInstance = new ConfigurationManager();
    private BigTrackConfiguration bigTrackConfiguration;

    public static ConfigurationManager getInstance() {
        return ourInstance;
    }

    private ConfigurationManager() {
        // todo load conf. from XML and initialize dialect drivers
    }
}
