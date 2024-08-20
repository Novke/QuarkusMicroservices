package fon.mas.novica.quarkus.service;

import fon.mas.novica.quarkus.model.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.TaskCompletedNotif;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EmailService {

    @Inject
    ProducerTemplate template;
    final Logger log = LoggerFactory.getLogger(this.getClass());

    public void newAssignment(NewAssignmentNotif notification) {
        Map<String, Object> headers = new HashMap<>();

        headers.put("To", notification.email);
        headers.put("From", "donotreply");
        headers.put("Subject", "New assignment!");

        String body = String.format("""
                        New task has been assigned to %s %s by the supervisor %s
                        You are expected to finish the task no later than %s
                                                
                        PRIORITY: %s
                        Check the details for the task using id = %s
                                                
                        ====================================================================================================================
                        THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
                        IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
                        ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
                        ====================================================================================================================
                        """,
                notification.firstName,
                notification.lastName,
                notification.supervisor,
                notification.dueDate,
                notification.priority,
                notification.taskId,
                notification.email);

        sendEmail(body, headers);
    }

    public void taskCompleted(TaskCompletedNotif notification) {
        Map<String, Object> headers = new HashMap<>();

        headers.put("To", notification.email);
        headers.put("From", "donotreply");
        headers.put("Subject", "Task completed!");

        String body = String.format("""
                        Hello %s %s,
                                            
                        The task "%s" with ID %d, issued for assignee %s has been marked as completed. As a task supervisor it is in your
                        responsibility to assure the task has been completed properly.
                                            
                        PRIORITY: %s
                        Due Date: %s
                                            
                        Thank you for completing the task on time!
                                            
                        ====================================================================================================================
                        THIS EMAIL AND ANY ATTACHMENTS IS INTENDED SOLELY FOR DESIGNATED RECIPIENTS AND MAY CONTAIN CONFIDENTAL INFORMATION.
                        IF YOU ARE NOT THE INTENDED RECIPIENT (%s) NOTIFY THE ORGANIZATION IMMEDIATELY AND DELETE ALL COPIES.
                        ANY UNAUTHORIZED REVIEW, USE OR DISCLOSURE IS PROHIBITED.
                        ====================================================================================================================
                        """,

                notification.firstName,
                notification.lastName,
                notification.taskTitle,
                notification.taskId,
                notification.assigneeName,
                notification.taskPriority,
                notification.dueDate,
                notification.email);


        sendEmail(body, headers);
    }

    private void sendEmail(String body, Map<String, Object> headers) {
        log.info("Sending email...");
        template.sendBodyAndHeaders("direct:start", body, headers);
        log.info("Email sent!");
    }

}
