package Configuration;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/30/16.
 */
@Data
public class BigTrackConfiguration {
    private List<DatabaseConfiguration> databaseConfigurations = new ArrayList<DatabaseConfiguration>();
}
