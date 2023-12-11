package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyBySquadInputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import static org.springframework.http.ResponseEntity.badRequest;

@JtechRestController("/api/v1/dailies")
@RequiredArgsConstructor
public class FinishDailyBySquadController {

    private final FinishDailyBySquadInputGateway finishDailyBySquadInputGateway;

    @PutMapping("/squad/{squad}")
    public ResponseEntity<DailyResponse> finishBySquad(@PathVariable String squad) {
        return finishDailyBySquadInputGateway.finish(squad)
                .map(DailyResponse::fromDomain)
                .map(Https::OK)
                .orElse(badRequest().build());
    }

}
