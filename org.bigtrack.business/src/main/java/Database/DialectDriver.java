package Database;

/**
 * Created by Alex on 5/30/16.
 */
public interface DialectDriver {
    public String getDatabaseTypeName();
    public String getCatalogTypeName ();
    public boolean getUserTrackingSupported();
    public DatabaseManager getDatabaseManager();
}
