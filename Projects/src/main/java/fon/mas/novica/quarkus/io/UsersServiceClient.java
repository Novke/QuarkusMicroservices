package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.exception.UsersServiceUnavailableException;
import fon.mas.novica.quarkus.model.dto.users.UserInfo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/internal/users")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "users-proxy")
@ApplicationScoped
public interface UsersServiceClient {

    final Logger log = LoggerFactory.getLogger(UsersServiceClient.class);
    @POST
    @Path("/verify")
    @Retry(delay = 1500, maxRetries = 2)
    boolean verifyAuthorization(List<Long> ids);

    @GET
    @Path("{id}")
    @Retry(delay = 1500, maxRetries = 2)
//    @Fallback(fallbackMethod = "findUserByIdFallback")
    UserInfo findUserById(@PathParam("id") Long id);

//    default UserInfo findUserByIdFallback(Long id){
//        log.error("GRESKA PRI UZIMANJU OVOG ONOG SA ID " + id);
//        throw new RuntimeException();
//    }

    @PUT
    @Path("/{id}/add-task")
    @Retry(delay = 1500, maxRetries = 2)
    @Fallback(fallbackMethod = "increaseTaskCountFallback")
    void increaseTaskCount(@PathParam("id") Long assigneeId);

    default void increaseTaskCountFallback(Long id){
        log.error("UNSUCCESFUL TASK INCREASING!!!");
        throw new UsersServiceUnavailableException("Unsuccesful task increase");
    }
}
