package fon.mas.novica.quarkus.model.dto.project;


import fon.mas.novica.quarkus.model.enums.Status;
import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public class ProjectInfo {

    private String name;
    private String description;
    private Status status;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate createdDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate startDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dueDate;

    public ProjectInfo() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
}
