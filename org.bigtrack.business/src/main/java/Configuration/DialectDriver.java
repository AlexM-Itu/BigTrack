package Configuration;

/**
 * Created by Alex on 5/30/16.
 */
public interface DialectDriver {
    public String getCatalogTypeName ();
    public boolean getUserTrackingSupported();
}
