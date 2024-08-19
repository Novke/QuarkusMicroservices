package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.UserAlreadyEnabledException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserAlreadyEnabledExceptionHandler implements ExceptionMapper<UserAlreadyEnabledException> {
    @Override
    public Response toResponse(UserAlreadyEnabledException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage()).build();
    }
}
