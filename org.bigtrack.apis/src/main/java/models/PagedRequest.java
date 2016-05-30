package models;

import lombok.Data;

import javax.ws.rs.QueryParam;

/**
 * Created by Alex on 5/29/16.
 */
@Data
public class PagedRequest {
    @QueryParam("offset")
    protected Integer offset;

    @QueryParam("count")
    protected Integer count;
}
