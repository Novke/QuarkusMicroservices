package fon.mas.novica.quarkus.model.entity;

import fon.mas.novica.quarkus.model.enums.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class ProjectEntity extends PanacheEntity {

    @Column(nullable = false)
    public String name;
    @Column(length = 3000)
    public String description;
    public LocalDate startDate;
    public LocalDate dueDate;
    public LocalDate endDate;
    public LocalDate createdDate;
    public LocalDate updatedDate;
    public Status status = Status.CREATED;
    @Column(nullable = false)
    public Long supervisorId;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<TaskEntity> tasks = new ArrayList<>();

    public void addTask(TaskEntity task){
        task.project = this;
        tasks.add(task);
    }

    /// FUNKCIJE

    public static List<ProjectEntity> findActiveProjects(){
        return list("status = ?1 or status = ?2", Status.CREATED, Status.IN_PROGRESS);
    }

}
