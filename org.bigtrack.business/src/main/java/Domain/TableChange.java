package Domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class TableChange {
    private long id;
    private int tableId;
    private int operationTypeId;
    private Date timestamp;
    private String user;
    private byte oprationTypeId;
    private OperationType operationType;
    private List<ColumnChange> columnChanges;
    private Table table;
}
