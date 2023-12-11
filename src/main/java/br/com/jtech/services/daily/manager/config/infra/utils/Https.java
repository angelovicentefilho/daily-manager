package br.com.jtech.services.daily.manager.config.infra.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class Https {
    public static <T> ResponseEntity<T> CREATED(T body) {
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<T> OK(T body) {
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
