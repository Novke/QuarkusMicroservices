package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.dto.notification.TaskCompletedNotif;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
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
    void notifyAssignee(NewAssignmentNotif notification);

    @POST
    @Path("/task-completed")
    void notifyTaskCompleted(TaskCompletedNotif notification);
}
