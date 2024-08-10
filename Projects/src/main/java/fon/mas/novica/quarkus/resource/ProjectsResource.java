package fon.mas.novica.quarkus.resource;


import fon.mas.novica.quarkus.model.dto.project.CreateProjectCmd;
import fon.mas.novica.quarkus.model.dto.project.ProjectDetails;
import fon.mas.novica.quarkus.model.dto.project.ProjectInfo;
import fon.mas.novica.quarkus.model.dto.task.CreateTaskCmd;
import fon.mas.novica.quarkus.model.dto.task.TaskInfo;
import fon.mas.novica.quarkus.model.enums.Status;
import fon.mas.novica.quarkus.service.ProjectsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectsResource {

    @Inject
    ProjectsService projectsService;

    @POST
    public Response createProject(CreateProjectCmd cmd) {
        System.out.println("Recieved cmd: " + cmd);
        ProjectInfo projectInfo = projectsService.createBlankProject(cmd);
        return Response.status(Response.Status.CREATED).entity(projectInfo).build();
    }

    @GET
    public Response getActiveProjects() {
        List<ProjectInfo> activeProjects = projectsService.findActiveProjects();
        return Response.ok(activeProjects).build();
    }

    @GET
    @Path("/all")
    public Response getAllProjects() {
        List<ProjectDetails> allProjects = projectsService.findAllProjects();
        return Response.ok(allProjects).build();
    }

    @POST
    @Path("/{id}")
    public Response addTask(@PathParam("id") Long id, CreateTaskCmd cmd) {
        TaskInfo taskInfo = projectsService.addTask(id, cmd);
        return Response.ok(taskInfo).build();
    }

    @GET
    @Path("/{id}/info")
    public Response showProjectDetails(@PathParam("id") Long id) {
        ProjectDetails projectDetails = projectsService.showProjectDetails(id);
        return Response.ok(projectDetails).build();
    }

    @PATCH
    @Path("/tasks/{id}/start")
    public Response setTaskInProgress(@PathParam("id") Long id) {
        TaskInfo taskInfo = projectsService.setTaskStatus(id, Status.IN_PROGRESS);
        return Response.ok(taskInfo).build();
    }

    @PATCH
    @Path("/tasks/{id}/done")
    public Response setTaskFinished(@PathParam("id") Long id) {
        TaskInfo taskInfo = projectsService.setTaskStatus(id, Status.FINISHED);
        return Response.ok(taskInfo).build();
    }
}
