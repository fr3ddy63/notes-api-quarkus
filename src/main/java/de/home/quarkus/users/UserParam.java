package de.home.quarkus.users;

import de.home.quarkus.common.BaseParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.Optional;

public class UserParam extends BaseParam {

    /**
     * Records per page.
     */
    @Min(0)
    @Max(16)
    @DefaultValue("8")
    @QueryParam("rpp")
    private Integer rpp;

    public UserParam() {
    }

    @Override
    public int getRpp() {
        return rpp;
    }
}
