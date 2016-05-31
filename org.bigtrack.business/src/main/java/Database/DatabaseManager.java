package Database;

import Domain.Table;
import Domain.TableChange;


import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
public interface DatabaseManager {
    List<Table> getDatabaseTables();
    List<String> getTableColumns(int tableId);
    List<TableChange> findChangesets(int tableId, ChangesetSearchOptions searchOptions);
}
