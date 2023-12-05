package br.com.jtech.services.daily.manager.application.core.domains.daily;

import java.util.Arrays;

public enum StatusBlocker {
    OPEN,
    IN_PROGRESS,
    RESOLVED;

    public static StatusBlocker getStatus(String status) {
        return Arrays.stream(StatusBlocker.values())
                .filter(statusBlocker -> status.equalsIgnoreCase(statusBlocker.toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status cannot be null!"));
    }
}
