package Database;

import Domain.MapperConfiguration;
import Domain.Table;
import Domain.TableChange;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.mapping.Result;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 6/11/16.
 */
public class CassandraDatabaseManager implements DatabaseManager {
    private Cluster cluster;

    @Override
    public void setConnectionString(String connectionString) {
        MapperConfiguration.Configure();
        cluster = Cluster.builder().addContactPoint(connectionString).build();
    }

    @Override
    public List<Table> getDatabaseTables() {
       try(Session session = cluster.connect()){
           List<Table> result = new ArrayList<Table>();

           ResultSet rows = session.execute("SELECT name FROM TableNames");
           for (Row row : rows){ // todo convert to stream
               Table table = new Table();
               table.setId(row.getString("name"));
               table.setName(table.getId());
               result.add(table);
           }

           return result;
       }
    }

    @Override
    public List<String> getTableColumns(int tableId) {

        try(Session session = cluster.connect()){
            ResultSet rows = session.execute("select  * from TableChanges where tableName = "+ tableId + "limit 1;");

            Mapper<TableChange> mapper = getMapper(session);
            Result<TableChange> latestChange = mapper.map(rows);

            if (latestChange.one() == null)
                return null;

            return latestChange
                    .one()
                    .getColumnChanges()
                    .stream()
                    .map(ch-> ch.getColumnsName())
                    .collect (Collectors.toList());
        }
    }

    @Override
    public List<TableChange> findChangesets(int tableId, ChangesetSearchOptions searchOptions) {
        return null;
    }

    @Override
    public TableChange getChangesetDetails(long changesetId) {
        return null;
    }

    private Mapper<TableChange> getMapper (Session session){
        Mapper<TableChange> tableChangeMapper = new MappingManager(session).mapper(TableChange.class);
        return tableChangeMapper;
    }
}
