package controllers;

import Configuration.BigTrackConfiguration;
import Configuration.ConfigurationManager;
import Configuration.DatabaseConfiguration;
import models.DatabaseConfigurationModel;

import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/configuration")
public class ConfigurationController {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Path("/")
    public BigTrackConfiguration GetConfiguration (){
        BigTrackConfiguration configuration = new BigTrackConfiguration();

        List<DatabaseConfiguration> databaseConfigurations = configurationManager.getDatabaseConfigurations();
        for(DatabaseConfiguration databaseConfiguration : databaseConfigurations){
            DatabaseConfigurationModel databaseConfigurationModel = new DatabaseConfigurationModel();
            databaseConfigurationModel.setId (databaseConfiguration.getId());
            databaseConfigurationModel.setName (databaseConfiguration.getName());
            databaseConfigurationModel.setCatalogTypeName(databaseConfiguration.getDialectDriver().getCatalogTypeName());
            databaseConfigurationModel.setUserTrackingSupported(databaseConfiguration.getDialectDriver().getUserTrackingSupported());

            configuration.getDatabaseConfigurations().add(databaseConfigurationModel);
        }

        return configuration;
    }
}