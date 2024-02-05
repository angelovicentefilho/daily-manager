package br.com.jtech.services.daily.manager.application.ports.input.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyRequest;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

import java.util.Optional;

public interface FinishDailyInputGateway {

    Optional<Daily> finish(Daily daily);
}
