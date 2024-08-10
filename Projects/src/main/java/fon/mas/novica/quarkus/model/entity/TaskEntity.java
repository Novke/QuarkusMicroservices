package fon.mas.novica.quarkus.model.entity;

import fon.mas.novica.quarkus.model.enums.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class TaskEntity extends PanacheEntity {

    public enum Priority {
        LOW, NORMAL, HIGH
    }
    public String title;
    public String description;
    @Enumerated(EnumType.STRING)
    public Priority priority;
    public LocalDate dueDate;
    public LocalDate endDate;
    public LocalDate createdDate;
    public LocalDate updatedDate;
    public Long supervisorId;
    public Long assigneeId;
    @Enumerated(EnumType.STRING)
    public Status status = Status.CREATED;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    public ProjectEntity project;
}
