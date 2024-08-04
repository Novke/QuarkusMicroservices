package fon.mas.novica.quarkus.rest;

import fon.mas.novica.quarkus.model.dto.user.UserInfo;
import fon.mas.novica.quarkus.model.entity.UserEntity;
import fon.mas.novica.quarkus.service.UsersService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UsersService usersService;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    public List<UserInfo> getAllUsers(){
        return usersService.findAll();
    }
}
