package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.service.UsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@ApplicationScoped
@Path("/internal/users")
public class UsersIO {

    @Inject
    UsersService usersService;
    @Inject
    SecurityContext securityContext;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id){
        return Response.ok(usersService.findById(id)).build();
    }

    @POST
    @Path("/verify")
    public Response getUser(List<Long> ids){
        String username = securityContext.getUserPrincipal().getName();

        return Response.ok(usersService.checkAuthorization(username, ids)).build();
    }

    @PUT
    @Path("/{id}/add-task")
    public Response increaseTaskCount(@PathParam("id") Long id){
        return Response.ok(usersService.increaseTaskCount(id)).build();
    }
}
