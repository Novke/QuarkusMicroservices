package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.dto.notification.TaskCompletedNotif;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/notify")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "notifications-proxy")
@ApplicationScoped
public interface NotificationsServiceClient {

    final Logger log = LoggerFactory.getLogger(NotificationsServiceClient.class);

    @POST
    @Path("/new-assignment")
//    @Retry(delay = 1500, maxRetries = 2)
    @Fallback(fallbackMethod = "notifyAssigneeFallback")
    void notifyAssignee(NewAssignmentNotif notification);

    default void notifyAssigneeFallback(NewAssignmentNotif notification){
        log.error("GRESKA PRI OBAVESTAVANJU KORISNIKA!!! {}", notification);
    }

    @POST
    @Path("/task-completed")
//    @Retry(delay = 1500, maxRetries = 2)
    @Fallback(fallbackMethod = "notifyTaskCompletedFallback")
    void notifyTaskCompleted(TaskCompletedNotif notification);

    default void notifyTaskCompletedFallback(TaskCompletedNotif notification){
        log.error("GRESKA PRI OBAVESTAVANJU KORISNIKA!!! {}", notification);
    }
}
