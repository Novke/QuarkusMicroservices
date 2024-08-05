package fon.mas.novica.quarkus.service;

import fon.mas.novica.quarkus.model.dto.project.CreateProjectCmd;
import fon.mas.novica.quarkus.model.dto.project.ProjectDetails;
import fon.mas.novica.quarkus.model.dto.project.ProjectInfo;
import fon.mas.novica.quarkus.model.dto.task.CreateTaskCmd;
import fon.mas.novica.quarkus.model.dto.task.TaskInfo;
import fon.mas.novica.quarkus.model.enums.Status;

import java.util.List;

public interface ProjectsService {
    ProjectInfo createBlankProject(CreateProjectCmd cmd);

    List<ProjectInfo> findActiveProjects();

    List<ProjectInfo> findAllProjects();

    TaskInfo addTask(Long id, CreateTaskCmd cmd);

    ProjectDetails showProjectDetails(Long id);

    TaskInfo setTaskStatus(Long id, Status status);
}
