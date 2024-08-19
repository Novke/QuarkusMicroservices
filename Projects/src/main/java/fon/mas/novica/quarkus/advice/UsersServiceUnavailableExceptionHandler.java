package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.UsersServiceUnavailableException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsersServiceUnavailableExceptionHandler implements ExceptionMapper<UsersServiceUnavailableException> {
    @Override
    public Response toResponse(UsersServiceUnavailableException exception) {
        return Response
                .status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(exception.getMessage()).build();
    }
}
