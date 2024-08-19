package fon.mas.novica.quarkus.advice;

import java.io.Serializable;

public class ApiException extends Exception implements Serializable {
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}
