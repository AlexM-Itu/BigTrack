package models;

import lombok.Data;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class DatabaseConfigurationListItemModel{
    private int id;
    private String name;
    private String catalogTypeName;
    private boolean isUserTrackingSupported;
}
