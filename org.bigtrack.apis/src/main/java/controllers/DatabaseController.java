package controllers;

import Database.DatabaseManager;
import Configuration.ConfigurationManager;
import Domain.Table;
import models.ChangesetDetailsResponse;
import models.ChnagesetsRequest;
import models.DatabaseTableListItemModel;

import javax.jws.WebMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/database")
public class DatabaseController {

    private ConfigurationManager configurationManager = ConfigurationManager.getInstance();

    @Path("/{databaseId}/tables")
    @WebMethod(operationName = "GET")
    public List<DatabaseTableListItemModel> GetTablesList(@PathParam("databaseId") String databaseId){
        DatabaseManager databaseManager = configurationManager
                .getDatabaseConfigurations()
                .stream()
                .filter(conf-> conf.getId() == databaseId)
                .findFirst()
                .get();

        // todo error if not found

        List<Table> tables = databaseManager.getDatabaseTables ();

        return new Collectors.toList(tables
                .stream()
                .map(table -> {
                    DatabaseTableListItemModel model = new DatabaseTableListItemModel();
                    model.setId(table.getId());
                    model.setName(table.getName());
                    return model;
                }));
    }

    @Path("/{databaseId}/tables/{tableId}/columns")
    @WebMethod(operationName = "GET")
    public List<String> GetTableColumns(@PathParam("databaseId") String databaseId, @PathParam("tableId") int tableId){
        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/changesets")
    @WebMethod(operationName = "GET")
    public void GetChangesets (@PathParam("databaseId") String databaseId, @PathParam("tableId") int tableId, ChnagesetsRequest changesetsRequest){
        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/{changesetId}")
    @WebMethod(operationName = "GET")
    public ChangesetDetailsResponse GetChangesetDetails (@PathParam("databaseId") String databaseId, @PathParam("tableId") int tableId, @PathParam("changesetId") int changesetId){
        throw new RuntimeException("Not implemented");
    }
}
