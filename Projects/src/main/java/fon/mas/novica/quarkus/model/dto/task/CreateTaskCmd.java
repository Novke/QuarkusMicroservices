package fon.mas.novica.quarkus.model.dto.task;


import fon.mas.novica.quarkus.model.entity.TaskEntity;
import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public class CreateTaskCmd {

    private String title;
    private String description;
    private TaskEntity.Priority priority;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dueDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate endDate;
    private Long supervisorId;
    private Long assigneeId;

    public CreateTaskCmd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskEntity.Priority getPriority() {
        return priority;
    }

    public void setPriority(TaskEntity.Priority priority) {
        this.priority = priority;
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

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
}
