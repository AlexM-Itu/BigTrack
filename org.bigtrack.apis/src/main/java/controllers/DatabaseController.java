package controllers;

import models.ChangesetDetailsResponse;
import models.ChnagesetsRequest;
import models.DatabaseTableListItemModel;

import javax.jws.WebMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/database")
public class DatabaseController {

    @Path("/{databaseId}/tables")
    @WebMethod(operationName = "GET")
    public List<DatabaseTableListItemModel> GetTablesList(@PathParam("databaseId") int databaseId){
        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/columns")
    @WebMethod(operationName = "GET")
    public List<String> GetTableColumns(@PathParam("databaseId") int databaseId, @PathParam("tableId") int tableId){
        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/changesets")
    @WebMethod(operationName = "GET")
    public void GetChangesets (@PathParam("databaseId") int databaseId, @PathParam("tableId") int tableId, ChnagesetsRequest changesetsRequest){
        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/{changesetId}")
    @WebMethod(operationName = "GET")
    public ChangesetDetailsResponse GetChangesetDetails (@PathParam("databaseId") int databaseId, @PathParam("tableId") int tableId, @PathParam("changesetId") int changesetId){
        throw new RuntimeException("Not implemented");
    }
}
