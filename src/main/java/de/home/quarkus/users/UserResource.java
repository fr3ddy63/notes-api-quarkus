package de.home.quarkus.users;

import de.home.quarkus.notes.UserNotesResource;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class UserResource {

    @Inject
    UserService service;

    private final User user;

    public UserResource(User user) {

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
    public UserNotesResource getNotes() {
        return new UserNotesResource(this.user);
    }
}
