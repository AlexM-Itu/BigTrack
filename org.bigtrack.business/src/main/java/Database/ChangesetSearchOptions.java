package Database;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 5/31/16.
 */
@Data
public class ChangesetSearchOptions {
    private Integer offset;
    private Integer count;
    private Date fromDate;
    private Date toDate;
    private String user;
    private List<String> columns;
}
