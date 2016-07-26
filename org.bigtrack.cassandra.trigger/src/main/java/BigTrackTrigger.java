import org.apache.cassandra.db.ColumnFamily;
import org.apache.cassandra.db.RowMutation;
import org.apache.cassandra.triggers.ITrigger;
import org.apache.cassandra.utils.UUIDGen;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * Created by Alex on 7/25/16.
 */
public class BigTrackTrigger implements ITrigger {

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
            String updatedValue = update.metadata().getValueValidator(col).getString(col);

            InsertChange(operationUuid, columnId, columnName, null, operation, null, tableId, tableName, updatedValue);
        }

        return Collections.emptyList();
    }

    private void InsertChange(UUID operationUuid, String columnId, String columnName, String dbUser, int operation, String priorValue, String tableId, String tableName, String updatedValue) {

    }

    private String getColumnId(String tableId, String columnName) {
        return null;
    }

    private void RegisterColumn(String tableId, String columnName) {

    }

    private void RegisterTable(String tableName) {
        return;
    }

    private String getTableId(String tableName) {
        return null;
    }
}
