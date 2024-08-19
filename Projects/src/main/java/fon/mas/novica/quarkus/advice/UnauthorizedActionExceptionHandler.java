package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.ProjectNotFoundException;
import fon.mas.novica.quarkus.exception.UnauthorizedActionException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthorizedActionExceptionHandler implements ExceptionMapper<UnauthorizedActionException> {
    @Override
    public Response toResponse(UnauthorizedActionException exception) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(exception.getMessage()).build();
    }
}
