package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

import java.time.LocalDateTime;

public interface FindDailyBySquadAndDateOutputGateway {
    Daily findBySquadAndDate(String squad, LocalDateTime dateTime);
}
