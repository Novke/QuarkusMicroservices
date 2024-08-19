package fon.mas.novica.quarkus.exception;

public class UsersServiceUnavailableException extends RuntimeException{

    public UsersServiceUnavailableException() {
    }

    public UsersServiceUnavailableException(String message) {
        super(message);
    }

    public UsersServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsersServiceUnavailableException(Throwable cause) {
        super(cause);
    }
}
