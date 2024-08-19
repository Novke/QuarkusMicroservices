package fon.mas.novica.quarkus.advice;

import fon.mas.novica.quarkus.exception.UserAlreadyDisabledException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserAlreadyDisabledExceptionHandler implements ExceptionMapper<UserAlreadyDisabledException> {
    @Override
    public Response toResponse(UserAlreadyDisabledException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage()).build();
    }
}
