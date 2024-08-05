package fon.mas.novica.quarkus.io;

import fon.mas.novica.quarkus.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.dto.notification.TaskCompletedNotif;

public interface NotificationsServiceClient {
    void notifyAssignee(NewAssignmentNotif notification);

    void notifyTaskCompleted(TaskCompletedNotif notification);
}
