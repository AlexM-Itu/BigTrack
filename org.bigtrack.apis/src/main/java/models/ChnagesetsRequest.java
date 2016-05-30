package models;

import lombok.Data;

import javax.ws.rs.QueryParam;
import java.util.Date;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class ChnagesetsRequest extends PagedRequest {

    @QueryParam("fromDate")
    private Date fromDate;

    @QueryParam("toDate")
    private Date toDate;

    @QueryParam("user")
    private String user;

    @QueryParam("columns")
    private String columns;
}
