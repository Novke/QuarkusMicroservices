package fon.mas.novica.quarkus.model;

public class TaskCompletedNotif {

    public String firstName;
    public String lastName;
    public Long taskId;
    public String taskTitle;
    public String taskPriority;
    public String email;
    public String assigneeName;
    public String dueDate;

    public TaskCompletedNotif() {
    }

    public TaskCompletedNotif(String firstName, String lastName, Long taskId, String taskTitle, String taskPriority, String email, String assigneeName, String dueDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.email = email;
        this.assigneeName = assigneeName;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TaskCompletedNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", email='" + email + '\'' +
                ", assigneeName='" + assigneeName + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}
