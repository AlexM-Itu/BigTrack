package Configuration;

import Database.DatabaseManager;
import lombok.Getter;
import java.lang.String;
import java.util.UUID;
import java.util.List;

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

    public List<DatabaseConfiguration> getDatabaseConfigurations(){
        return bigTrackConfiguration.getDatabaseConfigurations();
    }

    public DatabaseManager getDatabaseManagerByDatabaseId (String databaseId){
        return getDatabaseConfigurations()
                .stream()
                .filter(conf-> conf.getId() == databaseId)
                .map(conf-> conf.getDialectDriver().getDatabaseManager())
                .findFirst()
                .get();
    }
}
