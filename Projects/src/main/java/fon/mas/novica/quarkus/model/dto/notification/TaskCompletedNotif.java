package fon.mas.novica.quarkus.model.dto.notification;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public class TaskCompletedNotif {

    private String firstName;
    private String lastName;
    private Long taskId;
    private String taskTitle;
    private String taskPriority;
    private String email;
    private String assigneeName;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dueDate;

    public TaskCompletedNotif() {
    }

    public TaskCompletedNotif(String firstName, String lastName, Long taskId, String taskTitle, String taskPriority, String email, String assigneeName, LocalDate dueDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskPriority = taskPriority;
        this.email = email;
        this.assigneeName = assigneeName;
        this.dueDate = dueDate;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
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
                ", dueDate=" + dueDate +
                '}';
    }
}
