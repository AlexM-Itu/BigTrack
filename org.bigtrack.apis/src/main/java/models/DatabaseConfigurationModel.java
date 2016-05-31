package models;

import lombok.Data;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class DatabaseConfigurationModel {
    private int id;
    private String name;
    private String databaseTypeName;
    private String catalogTypeName;
    private boolean isUserTrackingSupported;
}
