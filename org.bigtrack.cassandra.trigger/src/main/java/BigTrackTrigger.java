import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import org.apache.cassandra.db.ColumnFamily;
import org.apache.cassandra.db.RowMutation;
import org.apache.cassandra.triggers.ITrigger;
import org.apache.cassandra.utils.UUIDGen;
import com.datastax.driver.core.Session;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Alex on 7/25/16.
 */
public class BigTrackTrigger implements ITrigger {
    private Cluster cluster = Cluster.builder().addContactPoint("localhost").build();

    @Override
    public Collection<RowMutation> augment(ByteBuffer partitionKey, ColumnFamily update) {
        String keyspaceName = update.metadata().ksName;
        String tableName = update.metadata().cfName;
        UUID operationUuid = UUIDGen.getTimeUUID();
        int operation = update.isMarkedForDelete() ? 3 : 2;

        // if not exist -> ins TableNames
        String tableId = getTableId(tableName);
        if (tableId == null) {
            RegisterTable(tableName);
            tableId = getTableId(tableName);
        }

        //prepare common fields for change
        for (ByteBuffer col : update.getColumnNames()) {
            // if not exist -> int ColumnNames
            String columnName = update.metadata().comparator.getString(col);
            String columnId = getColumnId(tableId, columnName);
            if (columnId == null) {
                RegisterColumn(tableId, columnName);
                columnId = getColumnId(tableId, columnName);
            }

            //insert change
            String priorValue = getLastColumnValue(tableId, columnId);
            String updatedValue = update.metadata().getValueValidator(col).getString(col);

            InsertChange(operationUuid, columnId, columnName, null, operation, priorValue, tableId, tableName, updatedValue);
        }

        return Collections.emptyList();
    }

    private String getLastColumnValue (String tableId, String columnId){
        try(Session session = cluster.connect()){
            Row result = session.execute(session.prepare("select \"updatedvalue\" from \"TableChanges\" where \"tableid\" = ? and \"columnid\" = ? limit 1").bind(tableId, columnId)).one();
            if (result == null)
                return null;

            return result.getString("updatedvalue");
        }
    }

    private void InsertChange(UUID operationUuid, String columnId, String columnName, String dbUser, int operation, String priorValue, String tableId, String tableName, String updatedValue) {
        try(Session session = cluster.connect()){
            Date operationDate = new Date();
            session.execute(
                    session.prepare("insert into \"TableChanges\"(\"id\", \"changeid\", \"changetimestamp\", \"columnid\", \"columnname\", \"dbuser\", \"operation\", \"priorvalue\", \"updatedvalue\", \"tableid\", \"tablename\")\n" +
                    "values(uuid(), ? , ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                            .bind(operationUuid.toString(), operationDate, columnId, columnName, dbUser, operation, priorValue, updatedValue, tableId, tableName));
        }
    }

    private String getColumnId(String tableId, String columnName) {
        try(Session session = cluster.connect()){
            Row result = session.execute(session.prepare("select \"id\" from \"TableColumns\" where \"tableid\" = ? and \"columnname\" = ?").bind(tableId, columnName)).one();
            if (result == null)
                return null;

            return result.getString("id");
        }
    }

    private void RegisterColumn(String tableId, String columnName) {
        try(Session session = cluster.connect()){
            session.execute(session.prepare("insert into \"TableColumns\"(\"id\", \"tableid\", \"columnname\", \"created\") values(uuid(), ?, ?, dateof(now()))").bind(tableId, columnName));
        }
    }

    private void RegisterTable(String tableName) {
        try(Session session = cluster.connect()){
            session.execute(session.prepare("insert into \"TableNames\" (\"id\", \"name\", \"created\") values(uuid(), ?, dateof(now()))").bind(tableName));
        }
    }

    private String getTableId(String tableName) {
        try(Session session = cluster.connect()){
            Row result = session.execute(session.prepare("select \"id\" from \"TableNames\" where \"name\" = ?").bind(tableName)).one();
            if (result == null)
                return null;

            return result.getString("id");
        }
    }
}
