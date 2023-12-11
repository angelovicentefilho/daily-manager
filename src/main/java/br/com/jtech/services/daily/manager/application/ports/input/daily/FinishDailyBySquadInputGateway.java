package br.com.jtech.services.daily.manager.application.ports.input.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

import java.util.Optional;

public interface FinishDailyBySquadInputGateway {
    Optional<Daily> finish(String squad);
}
