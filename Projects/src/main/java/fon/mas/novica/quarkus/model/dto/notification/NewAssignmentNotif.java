package fon.mas.novica.quarkus.model.dto.notification;

import java.time.LocalDate;

public class NewAssignmentNotif {
    private String firstName;
    private String lastName;
    private String supervisor;
    private String email;
    private LocalDate dueDate;
    private String priority;
    private Long taskId;

    public NewAssignmentNotif() {
    }

    public NewAssignmentNotif(String firstName, String lastName, String supervisor, String email, LocalDate dueDate, String priority, Long taskId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.supervisor = supervisor;
        this.email = email;
        this.dueDate = dueDate;
        this.priority = priority;
        this.taskId = taskId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "NewAssignmentNotif{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", email='" + email + '\'' +
                ", dueDate=" + dueDate +
                ", priority='" + priority + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}
