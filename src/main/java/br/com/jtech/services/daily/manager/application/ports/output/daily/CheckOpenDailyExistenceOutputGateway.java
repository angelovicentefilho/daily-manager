package br.com.jtech.services.daily.manager.application.ports.output.daily;

import java.util.UUID;

public interface CheckOpenDailyExistenceOutputGateway {

    boolean exists(final UUID dailyId);
}
