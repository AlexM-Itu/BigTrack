package Domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class TableChnage {
    private long id;
    private String tableName;
    private int operationTypeId;
    private Date timestamp;
    private String user;
    private byte oprationTypeId;
    private OperationType operationType;
    private List<ColumnChange> columnChanges;
}
