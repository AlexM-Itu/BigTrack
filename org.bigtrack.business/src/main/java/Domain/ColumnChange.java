package Domain;

import lombok.Data;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class ColumnChange {
    private long id;
    private long tableChangeId;
    private String columnName;
    private String priorValue;
    private String updatedValue;
}
