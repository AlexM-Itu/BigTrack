package Domain;

import lombok.Data;

/**
 * Created by Alex on 6/11/16.
 */
@Data
public class ColumnChange {
    private String columnsName;
    private String oldValue;
    private String newValue;
}
