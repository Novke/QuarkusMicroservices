package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.BadCredentialsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadCredentialsExceptionHandler implements ExceptionMapper<BadCredentialsException> {
    @Override
    public Response toResponse(BadCredentialsException exception) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(exception.getMessage()).build();
    }
}
