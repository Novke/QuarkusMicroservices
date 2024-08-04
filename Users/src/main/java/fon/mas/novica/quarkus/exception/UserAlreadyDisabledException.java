package fon.mas.novica.quarkus.exception;

public class UserAlreadyDisabledException extends RuntimeException{

    public UserAlreadyDisabledException() {
    }

    public UserAlreadyDisabledException(String message) {
        super(message);
    }

    public UserAlreadyDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyDisabledException(Throwable cause) {
        super(cause);
    }
}
