package de.home.quarkus.notes;

import de.home.quarkus.common.BaseParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class NoteParam extends BaseParam {

    /**
     * Records per page.
     */
    @Min(0)
    @Max(32)
    @DefaultValue("16")
    @QueryParam("rpp")
    private Integer rpp;

    public NoteParam() {
    }

    @Override
    public int getRpp() {
        return rpp;
    }
}
