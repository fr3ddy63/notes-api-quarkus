package de.home.quarkus.users;

import de.home.quarkus.notes.UserNotesResource;
import io.quarkus.arc.Unremovable;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Dependent
@Unremovable
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    private User user;

//    public UserResource(User user) {
//
//        this.user = user;
//    }

    public UserResource() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    @GET
    public Response getUser() {

        return Response.ok(this.user).build();
    }

    @DELETE
    public Response deleteUser() {

        this.service.remove(this.user);
        return Response.ok().build();
    }

    @Path("/notes")
    public UserNotesResource getNotes(@Context ResourceContext context) {

//        UserNotesResource userNotesResource = context.getResource(UserNotesResource.class);
//        userNotesResource.setUser(this.user);
//        return userNotesResource;

        UserNotesResource userNotesResource = CDI.current().select(UserNotesResource.class).get();
        userNotesResource.setUser(this.user);
        return userNotesResource;
    }
}
