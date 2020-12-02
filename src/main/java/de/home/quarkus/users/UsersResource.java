package de.home.quarkus.users;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UserService service;

    @POST
    public Response postUser(@Context UriInfo uriInfo, @Valid UserPostable userPostable) {

        User entity = userPostable.toEntity();
        this.service.persist(entity);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(entity.getUsername())
                .build();

        return Response.created(location).build();
    }

    @GET
    public Response getUsers(@Valid @BeanParam UserParam param) {

        return Response.ok(this.service.find(param)).build();
    }

    @Path("/{username}")
    public UserResource getUserResource(@Context ResourceContext context, @PathParam("username") String username) {

//        UserResource userResource = context.getResource(UserResource.class);
//        userResource.setUser(this.service.findByName(username).orElseThrow(NotFoundException::new));
//        return userResource;

        UserResource userResource = CDI.current().select(UserResource.class).get();
        userResource.setUser(this.service.findByName(username).orElseThrow(NotFoundException::new));
        return userResource;

//        return new UserResource(this.service.findByName(username).orElseThrow(NotFoundException::new));
    }
}
