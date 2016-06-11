package Database;

import Domain.Table;
import Domain.TableChange;

import java.util.List;

/**
 * Created by Alex on 6/11/16.
 */
public class CassandraDatabaseManager implements DatabaseManager {
    private String connectionString;

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public List<Table> getDatabaseTables() {
        return null;
    }

    @Override
    public List<String> getTableColumns(int tableId) {
        return null;
    }

    @Override
    public List<TableChange> findChangesets(int tableId, ChangesetSearchOptions searchOptions) {
        return null;
    }

    @Override
    public TableChange getChangesetDetails(long changesetId) {
        return null;
    }
}
