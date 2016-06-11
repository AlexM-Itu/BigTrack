package Configuration;

import Database.DatabaseManager;
import Database.DialectDriver;

/**
 * Created by Alex on 6/11/16.
 */
public class CassandraDialectDriver implements DialectDriver {
    @Override
    public String getDatabaseTypeName() {
        return "Cassandra";
    }

    @Override
    public String getCatalogTypeName() {
        return "Table";
    }

    @Override
    public boolean getUserTrackingSupported() {
        return false; //todo elaborate this
    }

    @Override
    public DatabaseManager getDatabaseManager() {
        return null;
    }
}
