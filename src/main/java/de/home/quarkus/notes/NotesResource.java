package de.home.quarkus.notes;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotesResource {

    @Inject
    NoteService service;

    @GET
    public Response getNotes(@Valid @BeanParam NoteParam param) {

        return Response.ok(this.service.find(param)).build();
    }

    @Path("/{id}")
    public NoteResource getNoteResource(@PathParam("id") Long id) {

        return new NoteResource(this.service.find(id).orElseThrow(NotFoundException::new));
    }
}