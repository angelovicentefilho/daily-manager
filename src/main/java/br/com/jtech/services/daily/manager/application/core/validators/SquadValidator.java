package br.com.jtech.services.daily.manager.application.core.validators;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class SquadValidator {

    public static void validateId(String id) {
        checkNotNull(id, "ID cannot be null!");
        validateUUID(id);
    }

    public static void validateIdNotNull(UUID id) {
        checkNotNull(id, "ID user cannot be null for update!");
    }

    private static void checkNotNull(Object object, String errorMessage) {
        if (object == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validateUUID(String id) {
        try {
            UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid squad ID!");
        }
    }
}
