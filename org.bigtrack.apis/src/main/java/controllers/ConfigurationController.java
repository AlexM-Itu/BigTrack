package controllers;

import models.DatabaseConfigurationListItemModel;

import javax.ws.rs.Path;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/configuration")
public class ConfigurationController {

    @Path("/")
    public DatabaseConfigurationListItemModel GetConfiguration (){
        throw new RuntimeException("Not implemented");
    }
}
