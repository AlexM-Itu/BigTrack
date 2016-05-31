package Configuration;

import lombok.Data;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class DatabaseConfiguration {
    private String id;
    private String name;
    private String connectionString;
    private String dialectDriverName;
    private DialectDriver dialectDriver;
}
