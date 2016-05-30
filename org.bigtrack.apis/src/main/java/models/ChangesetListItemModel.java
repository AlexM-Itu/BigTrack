package models;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class ChangesetListItemModel {
    private Date timestamp;
    private String user;
    private List<String> columns;
    private String operation; // todo change with enum
}
