package fon.mas.novica.quarkus.model.dto.project;


import fon.mas.novica.quarkus.model.dto.task.TaskInfo;
import fon.mas.novica.quarkus.model.enums.Status;
import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;
import java.util.List;

public class ProjectDetails {

    private Long id;
    private String name;
    private String description;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate startDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dueDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate endDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate createdDate;
    private Status status;
    private String supervisorName;
    private List<TaskInfo> tasks;

    public ProjectDetails() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public List<TaskInfo> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskInfo> tasks) {
        this.tasks = tasks;
    }
}
