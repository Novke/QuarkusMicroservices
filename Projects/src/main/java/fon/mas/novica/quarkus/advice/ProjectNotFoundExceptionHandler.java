package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.ProjectNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProjectNotFoundExceptionHandler implements ExceptionMapper<ProjectNotFoundException> {
    @Override
    public Response toResponse(ProjectNotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage()).build();
    }
}
