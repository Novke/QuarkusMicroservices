package fon.mas.novica.quarkus.rest;

import fon.mas.novica.quarkus.model.dto.login.UpdatePasswordCmd;
import fon.mas.novica.quarkus.model.dto.user.CreateUserCmd;
import fon.mas.novica.quarkus.model.dto.user.UserInfo;
import fon.mas.novica.quarkus.model.dto.user.UserInsight;
import fon.mas.novica.quarkus.service.UsersService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.http.HttpStatus;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UsersService usersService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @RolesAllowed("ROLE_ADMIN")
    public Response createUser(CreateUserCmd cmd){
        return Response.status(HttpStatus.SC_CREATED).entity(usersService.createUser(cmd)).build();
    }
    @POST
    @Path("/admin")
    @RolesAllowed("ROLE_ADMIN")
    public Response createAdmin(CreateUserCmd cmd){
        return Response.status(HttpStatus.SC_CREATED).entity(usersService.createAdmin(cmd)).build();
    }

    @GET
    @Authenticated
    public List<UserInfo> getActiveUsers(){
        return usersService.findActiveUsers();
    }
    @GET
    @Path("/all")
    @RolesAllowed("ROLE_ADMIN")
    public List<UserInsight> getAllUsers(){
        return usersService.findAllUsers();
    }

    @DELETE
    @Path("/{user}")
    @RolesAllowed("ROLE_ADMIN")
    public Response disableUser(@PathParam("user") String user){
        usersService.disableUser(user);
        return Response.status(HttpStatus.SC_ACCEPTED).build();
    }
    @PATCH
    @Path("/{user}")
    @RolesAllowed("ROLE_ADMIN")
    public Response enableUser(@PathParam("user") String user){
        usersService.enableUser(user);
        return Response.status(HttpStatus.SC_ACCEPTED).build();
    }

    @PUT
    @Authenticated
    public Response updatePassword(UpdatePasswordCmd cmd){
        usersService.updatePassword(cmd);
        return Response.status(HttpStatus.SC_ACCEPTED).build();
    }
}
