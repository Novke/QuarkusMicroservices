package fon.mas.novica.quarkus.model.dto.project;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDate;

public class CreateProjectCmd {

    private String name;
    private String description;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate startDate;
    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate dueDate;
    private Long supervisorId;

    public CreateProjectCmd() {
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

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return "CreateProjectCmd{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                ", supervisorId=" + supervisorId +
                '}';
    }
}
