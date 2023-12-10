package br.com.jtech.services.daily.manager.application.core.exceptions.squad;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SquadNotFoundException extends RuntimeException {
    public SquadNotFoundException() {
    }

    public SquadNotFoundException(String message) {
        super(message);
    }

    public SquadNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SquadNotFoundException(Throwable cause) {
        super(cause);
    }

    public SquadNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
