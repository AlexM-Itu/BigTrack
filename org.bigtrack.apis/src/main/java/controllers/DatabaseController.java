package controllers;


import Configuration.ConfigurationManager;
import Database.ChangesetSearchOptions;
import Database.DatabaseManager;
import Domain.Table;
import Domain.TableChange;
import models.ChangesetDetailsResponse;
import models.ChangesetListItemModel;
import models.ChnagesetsRequest;
import models.DatabaseTableListItemModel;

import javax.jws.WebMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 5/29/16.
 */
@Path("/database")
public class DatabaseController {

    private ConfigurationManager configurationManager;
    public DatabaseController()throws Exception{
        configurationManager = ConfigurationManager.getInstance();
    }

    @Path("/{databaseId}/tables")
    @WebMethod(operationName = "GET")
    public List<DatabaseTableListItemModel> GetTablesList(@PathParam("databaseId") String databaseId){

        DatabaseManager databaseManager = configurationManager.getDatabaseManagerByDatabaseId(databaseId);
        // todo error if not found

        List<Table> tables = databaseManager.getDatabaseTables();

//        return new tables
//                .stream()
//                .map(table -> {
//                    DatabaseTableListItemModel model = new DatabaseTableListItemModel();
//                    model.setId(table.getId());
//                    model.setName(table.getName());
//                    return model;
//                })
//                .collect(Collectors.toList());

        throw new RuntimeException("Not implemented");
    }

    @Path("/{databaseId}/tables/{tableId}/columns")
    @WebMethod(operationName = "GET")
    public List<String> GetTableColumns(@PathParam("databaseId") String databaseId, @PathParam("tableId") String tableId){
        DatabaseManager databaseManager = configurationManager.getDatabaseManagerByDatabaseId(databaseId);
        // todo error if not found

        return databaseManager.getTableColumns(tableId);
    }

    @Path("/{databaseId}/tables/{tableId}/changesets")
    @WebMethod(operationName = "GET")
    public List<ChangesetListItemModel> FindChangesets (@PathParam("databaseId") String databaseId, @PathParam("tableId") String tableId, ChnagesetsRequest changesetsRequest){

        DatabaseManager databaseManager = configurationManager.getDatabaseManagerByDatabaseId(databaseId);
        // todo error if not found

        ChangesetSearchOptions searchOptions = new ChangesetSearchOptions();
        searchOptions.setOffset(changesetsRequest.getOffset());
        searchOptions.setCount(changesetsRequest.getCount());
        searchOptions.setFromDate(changesetsRequest.getFromDate());
        searchOptions.setToDate(changesetsRequest.getToDate());
        searchOptions.setUser(changesetsRequest.getUser());
        searchOptions.setColumns(changesetsRequest.getColumns());

        List<TableChange> tableChnages = databaseManager.findChangesets (tableId, searchOptions);

        return tableChnages
                .stream()
                .map(ch-> {
                    ChangesetListItemModel model = new ChangesetListItemModel();
                    model.setTimestamp(ch.getTimestamp());
                    model.setUser(ch.getUser());
                    model.setColumns(ch
                            .getColumnChanges()
                            .stream()
                            .map(col-> col.getColumnName())
                            .collect(Collectors.toList()));
                    model.setOperation(ch.getOperationType().getName());

                    return model;
                })
                .collect(Collectors.toList());
    }

    @Path("/{databaseId}/tables/{tableId}/{changesetId}")
    @WebMethod(operationName = "GET")
    public ChangesetDetailsResponse GetChangesetDetails (@PathParam("databaseId") String databaseId, @PathParam("tableId") String tableId, @PathParam("changesetId") String changesetId){

        DatabaseManager databaseManager = configurationManager.getDatabaseManagerByDatabaseId(databaseId);
        // todo error if not found

        TableChange changeset = databaseManager.getChangesetDetails(changesetId); // todo db id is never used

        ChangesetDetailsResponse response = new ChangesetDetailsResponse();
        response.setOperation(changeset.getOperationType().getName());
        response.setTimestamp(changeset.getTimestamp());

        if (changeset.getTimestamp().equals("Insert")){ // todo change with enum usage
            response.setPriorValues(changeset
                .getColumnChanges()
                .stream()
                .collect(Collectors.toMap(ch -> ch.getColumnName(), ch -> ch.getPriorValue())));
        }

        if (changeset.getTimestamp().equals("Delete")){ // todo change with enum usage
            response.setUpdatedValues(changeset
                .getColumnChanges()
                .stream()
                .collect(Collectors.toMap(ch-> ch.getColumnName(), ch-> ch.getUpdatedValue())));
        }

        return response;
    }
}
