package models;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class ChangesetDetailsResponse {
    private Map<String, String> priorValues;
    private Map<String, String> updatedValues;
    private String operation; // todo change with enum
    private Date timestamp;
}
