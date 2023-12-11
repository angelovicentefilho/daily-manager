package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyBySquadInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyBySquadOutputGateway;

import java.util.Optional;


public class FinishDailyBySquadUseCase implements FinishDailyBySquadInputGateway {

    private final FindDailyBySquadOutputGateway findDailyBySquadOutputGateway;

    public FinishDailyBySquadUseCase(FindDailyBySquadOutputGateway findDailyBySquadOutputGateway) {
        this.findDailyBySquadOutputGateway = findDailyBySquadOutputGateway;
    }

    @Override
    public Optional<Daily> finish(String squad) {
        var daily = findDailyBySquadOutputGateway.findBySquad(squad);
        return Optional.empty();
    }
}
