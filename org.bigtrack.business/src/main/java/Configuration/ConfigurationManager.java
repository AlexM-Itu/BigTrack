package Configuration;

import Database.DatabaseManager;
import Database.DialectDriver;
import com.thoughtworks.xstream.XStream;
import javafx.scene.text.TextBuilder;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.String;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private BigTrackConfiguration bigTrackConfiguration;
    private final String configurationFileName = "bigTrack.xml";

    public static ConfigurationManager getInstance() throws Exception{
        if (instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

    private ConfigurationManager() throws Exception{
        XStream serializer = new XStream();
        bigTrackConfiguration = (BigTrackConfiguration) serializer.fromXML (new File(configurationFileName));
        for (DatabaseConfiguration configuration : bigTrackConfiguration.getDatabaseConfigurations()){
            configuration.setDialectDriver((DialectDriver)Class.forName(configuration.getDialectDriverName()).newInstance());
        }
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
