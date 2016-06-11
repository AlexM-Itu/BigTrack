package Domain;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;

/**
 * Created by Alex on 6/11/16.
 */
@Table (keyspace = "todo", name="tableChanges")
@Data
public class TableChange {
    @PartitionKey
    private UUID id;
    private Set<ColumnChange> columnChanges;
    private int dbUser;
    private Operations operation; // todo register codec
    private String tableName;
    private Date timestamp;
}
