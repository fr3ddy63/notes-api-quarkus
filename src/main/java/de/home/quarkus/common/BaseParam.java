package de.home.quarkus.common;

import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public abstract class BaseParam implements Pageable {

    @Min(0)
    @DefaultValue("0")
    @QueryParam("page")
    protected Integer page;

    public BaseParam() {
    }

    @Override
    public int getPage() {
        return page;
    }
}
