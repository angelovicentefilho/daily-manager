package br.com.jtech.services.daily.manager.application.core.exceptions.daily;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DailyNotFoundException extends RuntimeException {
    public DailyNotFoundException() {
    }

    public DailyNotFoundException(String message) {
        super(message);
    }

    public DailyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DailyNotFoundException(Throwable cause) {
        super(cause);
    }

    public DailyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
