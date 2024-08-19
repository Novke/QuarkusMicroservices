package fon.mas.novica.quarkus.service.impl;

import fon.mas.novica.quarkus.model.dto.notification.NewAssignmentNotif;
import fon.mas.novica.quarkus.model.dto.notification.TaskCompletedNotif;
import fon.mas.novica.quarkus.model.dto.project.CreateProjectCmd;
import fon.mas.novica.quarkus.model.dto.project.ProjectDetails;
import fon.mas.novica.quarkus.model.dto.project.ProjectInfo;
import fon.mas.novica.quarkus.model.dto.task.CreateTaskCmd;
import fon.mas.novica.quarkus.model.dto.task.TaskInfo;
import fon.mas.novica.quarkus.model.dto.users.UserInfo;
import fon.mas.novica.quarkus.model.entity.ProjectEntity;
import fon.mas.novica.quarkus.model.entity.TaskEntity;
import fon.mas.novica.quarkus.model.enums.Status;
import fon.mas.novica.quarkus.exception.ProjectNotFoundException;
import fon.mas.novica.quarkus.exception.TaskNotFoundException;
import fon.mas.novica.quarkus.exception.UnauthorizedActionException;
import fon.mas.novica.quarkus.exception.UserNotFoundException;
import fon.mas.novica.quarkus.io.NotificationsServiceClient;
import fon.mas.novica.quarkus.io.UsersServiceClient;
import fon.mas.novica.quarkus.service.ProjectsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
@Transactional
public class ProjectsServiceImpl implements ProjectsService {

    @RestClient
    UsersServiceClient usersService;

    @Inject
    NotificationsServiceClient notificationsService;
    @Inject
    ModelMapper mapper;

    @Override
    public ProjectInfo createBlankProject(CreateProjectCmd cmd) {
        ProjectEntity project = mapper.map(cmd, ProjectEntity.class);
        project.createdDate = LocalDate.now();

        UserInfo supervisor = findUserById(cmd.getSupervisorId());
        project.supervisorId = supervisor.getId();

        project.persist();
        return mapper.map(project, ProjectInfo.class);
    }

    @Override
    public List<ProjectDetails> findAllProjects() {
        return ProjectEntity.listAll().stream()
                .map(p -> mapper.map(p, ProjectDetails.class))
                .toList();
    }

    @Override
    public List<ProjectInfo> findActiveProjects() {
        return ProjectEntity.find("status != ?1", Status.FINISHED).list().stream()
                .map(p -> mapper.map(p, ProjectInfo.class))
                .toList();
    }

    @Override
    public TaskInfo addTask(Long id, CreateTaskCmd cmd) {

        ProjectEntity project = ProjectEntity.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project with id " + id + " not found!");
        }

        throwIfUnauthorized(List.of(project.supervisorId));

        UserInfo assignee = findUserById(cmd.getAssigneeId());
        UserInfo supervisor = findUserById(cmd.getSupervisorId());

        TaskEntity task = mapper.map(cmd, TaskEntity.class);
        project.addTask(task);
        task.createdDate = LocalDate.now();
        task.assigneeId = assignee.getId();
        task.supervisorId = supervisor.getId();

        task.persist();
        TaskInfo taskInfo = mapper.map(task, TaskInfo.class);
        taskInfo.setAssigneeName(assignee.getFullName());
        taskInfo.setSupervisorName(supervisor.getFullName());

        notifyAssignee(assignee, supervisor, taskInfo);

        return taskInfo;
    }

    @Override
    public ProjectDetails showProjectDetails(Long id) {
        ProjectEntity project = ProjectEntity.findById(id);
        if (project == null) {
            throw new RuntimeException("Project with id " + id + " not found!");
        }

        List<TaskInfo> tasks = new ArrayList<>();
        project.tasks.forEach(te -> {
            TaskInfo taskInfo = taskEntityToTaskInfo(te);
            tasks.add(taskInfo);
        });

        ProjectDetails projectDetails = mapper.map(project, ProjectDetails.class);
        projectDetails.setSupervisorName(getUserFullNameSilently(project.supervisorId));
        projectDetails.setTasks(tasks);

        return projectDetails;
    }

    @Override
    public TaskInfo setTaskStatus(Long id, Status status) {
        TaskEntity task = TaskEntity.findById(id);
        if (task == null) {
            throw new TaskNotFoundException(String.format("Task with id %s not found!", id));
        }

        throwIfUnauthorized(List.of(task.assigneeId, task.supervisorId));

        task.status = status;
        task.updatedDate = LocalDate.now();
        if (status == Status.FINISHED) task.endDate = LocalDate.now();

        task.persist();
        TaskInfo taskInfo = taskEntityToTaskInfo(task);

        if (status == Status.FINISHED) {
            usersService.increaseTaskCount(task.assigneeId);

            if (!Objects.equals(task.assigneeId, task.supervisorId)) {
                UserInfo assignee = findUserById(task.assigneeId);
                UserInfo supervisor = findUserById(task.supervisorId);

                notifyTaskCompleted(assignee, supervisor, taskInfo);
            }
        }

        return taskInfo;
    }

    private void notifyAssignee(UserInfo assignee, UserInfo supervisor, TaskInfo taskInfo) {
        NewAssignmentNotif notification = new NewAssignmentNotif(assignee.getFirstName(),
                assignee.getLastName(),
                supervisor.getFullName(),
                assignee.getEmail(),
                taskInfo.getDueDate(),
                taskInfo.getPriority().name(),
                taskInfo.getId());

        notificationsService.notifyAssignee(notification);
    }

    private void notifyTaskCompleted(UserInfo assignee, UserInfo supervisor, TaskInfo taskInfo) {
        TaskCompletedNotif notification = new TaskCompletedNotif(
                supervisor.getFirstName(),
                supervisor.getLastName(),
                taskInfo.getId(),
                taskInfo.getTitle(),
                taskInfo.getPriority().toString(),
                supervisor.getEmail(),
                assignee.getFullName(),
                taskInfo.getDueDate());

        notificationsService.notifyTaskCompleted(notification);
    }

    ///////////////////
    //  UTIL FUNCTIONS
    ///////////////////
    private void throwIfUnauthorized(List<Long> ids) {
        if (!usersService.verifyAuthorization(ids)) throw new UnauthorizedActionException();
    }

    private TaskInfo taskEntityToTaskInfo(TaskEntity entity) {
        TaskInfo taskInfo = mapper.map(entity, TaskInfo.class);
        taskInfo.setAssigneeName(getUserFullNameSilently(entity.assigneeId));
        taskInfo.setSupervisorName(getUserFullNameSilently(entity.supervisorId));

        return taskInfo;
    }

    private String getUserFullNameSilently(Long id) {
        try {
            return findUserById(id).getFullName();
        } catch (Exception ignored) {
            return "UNKNOWN";
        }
    }

    private UserInfo findUserById(Long id) {
        try {
            return usersService.findUserById(id);
        } catch (Exception ex) {
            logger.error("FIND USER BY ID ERROR!", ex);
            throw new UserNotFoundException("User with id " + id + " not found", ex);
        }
    }
}
