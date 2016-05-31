package Database;

import Domain.Table;

import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
public interface DatabaseManager {
    List<Table> getDatabaseTables();
}
