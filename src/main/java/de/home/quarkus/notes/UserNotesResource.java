package de.home.quarkus.notes;

import de.home.quarkus.users.User;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserNotesResource {

    @Inject
    NoteService noteService;

    private final User user;

    public UserNotesResource(User user) {

        this.user = user;
    }

    @POST
    public Response postUserNote(@Context UriInfo uriInfo, @Valid NotePostable notePostable) {

        Note entity = notePostable.toEntity();
        entity.setAuthor(this.user);
        this.noteService.persist(entity);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(entity.getId().toString())
                .build();

        return Response.created(location).build();
    }

    @GET
    public Response getUserNotes(@Valid @BeanParam NoteParam param) {

        return Response.ok(this.noteService.findByAuthor(this.user, param)).build();
    }
}
