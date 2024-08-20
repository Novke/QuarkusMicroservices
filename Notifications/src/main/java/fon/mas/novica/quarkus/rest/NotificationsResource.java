package fon.mas.novica.quarkus.rest;

import fon.mas.novica.quarkus.model.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.TaskCompletedNotif;
import fon.mas.novica.quarkus.service.EmailService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/notify")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationsResource {

    @Inject
    EmailService emailService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @POST
    @Path("/new-assignment")
    public Response notifyAssignee(NewAssignmentNotif notification){
        log.info("NEW ASSIGNMENT RECIEVED! {}", notification);
        emailService.newAssignment(notification);
        return Response.ok("Uspesno").build();
    }

    @POST
    @Path("/task-completed")
    public Response notifySupervisorTaskCompleted(TaskCompletedNotif notification){
        log.info("TASK COMPLETED! {}", notification);
        emailService.taskCompleted(notification);
        return Response.ok("Uspesno").build();
    }

}
