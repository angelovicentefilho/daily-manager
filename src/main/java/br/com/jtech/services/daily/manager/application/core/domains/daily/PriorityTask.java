package br.com.jtech.services.daily.manager.application.core.domains.daily;

import java.util.Arrays;

public enum PriorityTask {
    HIGH,
    MEDIUM, LOW_PRIORITY;

    public static PriorityTask getPriority(String priority) {
        return Arrays.stream(PriorityTask.values())
                .filter(priorityTask -> priority.equalsIgnoreCase(priorityTask.toString()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Priority cannot be null!"));
    }
}
