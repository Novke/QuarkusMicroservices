package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.service.UsersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
@Path("/internal/users")
public class UsersIO {

    @Inject
    UsersService usersService;
    @Inject
    SecurityContext securityContext;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Long id){
        log.info("GET USED RECIEVED");
        return Response.ok(usersService.findById(id)).build();
    }

    @POST
    @Path("/verify")
    public Response verify(List<Long> ids){
//        String username = securityContext.getUserPrincipal().getName();
        log.info("VERIFY REQUEST RECIEVED!");
        return Response.ok(true).build();
//        return Response.ok(usersService.checkAuthorization(username, ids)).build();
    }

    @PUT
    @Path("/{id}/add-task")
    public Response increaseTaskCount(@PathParam("id") Long id){
        log.error("INCR TASK REQUEST RECIEVED");
        return Response.ok(usersService.increaseTaskCount(id)).build();
    }
}
