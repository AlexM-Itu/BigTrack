package Database;

import Domain.Table;
import Domain.TableChange;


import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
public interface DatabaseManager {
    public List<Table> getDatabaseTables();
    public List<String> getTableColumns(int tableId);
    public List<TableChange> findChangesets(int tableId, ChangesetSearchOptions searchOptions);
    public TableChange getChangesetDetails (long changesetId);
}
