package br.com.jtech.services.daily.manager.application.core.exceptions.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeBadRequestException extends RuntimeException {
    public EmployeeBadRequestException() {
    }

    public EmployeeBadRequestException(String message) {
        super(message);
    }

    public EmployeeBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeBadRequestException(Throwable cause) {
        super(cause);
    }

    public EmployeeBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
