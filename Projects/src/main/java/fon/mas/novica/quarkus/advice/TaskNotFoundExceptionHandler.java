package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.ProjectNotFoundException;
import fon.mas.novica.quarkus.exception.TaskNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TaskNotFoundExceptionHandler implements ExceptionMapper<TaskNotFoundException> {
    @Override
    public Response toResponse(TaskNotFoundException exception) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage()).build();
    }
}
