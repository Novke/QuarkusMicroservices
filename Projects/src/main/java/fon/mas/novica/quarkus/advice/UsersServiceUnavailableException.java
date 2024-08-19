package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.UserNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsersServiceUnavailableException implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response
                .status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(exception.getMessage()).build();
    }
}
