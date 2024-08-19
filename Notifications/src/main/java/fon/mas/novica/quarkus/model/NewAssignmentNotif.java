package fon.mas.novica.quarkus.model;

public class NewAssignmentNotif {

    public String firstName;
    public String lastName;
    public String supervisor;
    public String email;
    public String dueDate;
    public String priority;
    public Long taskId;

    public NewAssignmentNotif() {
    }

    public NewAssignmentNotif(String firstName, String lastName, String supervisor, String email, String dueDate, String priority, Long taskId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.supervisor = supervisor;
        this.email = email;
        this.dueDate = dueDate;
        this.priority = priority;
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "NewAssignmentNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", email='" + email + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}
