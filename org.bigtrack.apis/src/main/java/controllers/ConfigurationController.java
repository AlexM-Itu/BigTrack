package controllers;

import Configuration.BigTrackConfiguration;
import Configuration.ConfigurationManager;
import Configuration.DatabaseConfiguration;
import models.DatabaseConfigurationModel;

import javax.ws.rs.Path;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/configuration")
public class ConfigurationController {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Path("/")
    public BigTrackConfiguration GetConfiguration (){
        BigTrackConfiguration configuration = new BigTrackConfiguration;
        configuration.setSessionId(configurationManager.getSessionId());

        List<DatabaseConfiguration> databaseConfigurations = configurationManager.getDatabaseConfigurations();
        for(int = 0; i< databaseConfigurations.size(); i++){
            DatabaseConfigurationModel databaseConfigurationModel = new DatabaseConfigurationModel();
            databaseConfigurationModel.setId (i);
            databaseConfigurationModel.setName (databaseConfigurations.Get(i).getName());
            databaseConfigurationModel.setCatalogTypeName(databaseConfigurations.Get(i).getDialectDriver().getCatalogTypeName());
            databaseConfigurationModel.setUserTrackingSupported(databaseConfigurations.Get(i).getDialectDriver().getUserTrackingSupported());

            configuration.getDatabaseConfigurations().add(databaseConfigurationModel);
        }

        return configuration;
    }
}