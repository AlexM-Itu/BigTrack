package models;

import lombok.Data;

import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class BigTrackConfigurationResponse {
    private String sessionId;
    private List<DatabaseConfigurationModel> databaseConfigurations;
}
