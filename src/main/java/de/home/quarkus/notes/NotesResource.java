package de.home.quarkus.notes;

import de.home.quarkus.utility.Links;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/notes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotesResource {

    @Inject
    NoteService service;

    @GET
    public Response getNotes(@Context UriInfo uriInfo, @Valid @BeanParam NoteParam param) {

        long notesCount = this.service.count(param);

        return Response.ok(this.service.find(param))
                .header("X-Total-Count", notesCount)
                .links(Links.getPaginationLinks(param, uriInfo, notesCount))
                .build();
    }

    @Path("/{id}")
    public NoteResource getNoteResource(@Context ResourceContext context, @PathParam("id") Long id) {

        NoteResource noteResource = context.getResource(NoteResource.class);
        noteResource.setNote(this.service.find(id).orElseThrow(NotFoundException::new));
        return noteResource;
    }
}
