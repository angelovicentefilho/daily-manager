package br.com.jtech.services.daily.manager.application.core.domains.daily;

import java.util.Arrays;

public enum StatusTask {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public static StatusTask of(String status) {
        return Arrays.stream(StatusTask.values())
                .filter(statusTask -> status.equalsIgnoreCase(statusTask.toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status cannot be null!"));

    }
}
