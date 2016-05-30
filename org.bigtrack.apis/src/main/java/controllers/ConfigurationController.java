package controllers;

import models.ConfigurationResponse;

import javax.ws.rs.Path;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/configuration")
public class ConfigurationController {

    @Path("/")
    public ConfigurationResponse GetConfiguration (){
        throw new RuntimeException("Not implemented");
    }
}
