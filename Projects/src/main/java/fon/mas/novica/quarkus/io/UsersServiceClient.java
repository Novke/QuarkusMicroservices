package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.model.dto.users.UserInfo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/internal/users")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "users-proxy")
@ApplicationScoped
public interface UsersServiceClient {

    @POST
    @Path("/verify")
    boolean verifyAuthorization(List<Long> ids);

    @GET
    @Path("{id}")
    UserInfo findUserById(@PathParam("id") Long id);

    @PUT
    @Path("/{id}/add-task")
    void increaseTaskCount(@PathParam("id") Long assigneeId);
}
