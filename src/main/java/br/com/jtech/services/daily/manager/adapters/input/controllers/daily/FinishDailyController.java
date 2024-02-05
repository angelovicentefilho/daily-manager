package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyFinishRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.input.daily.FinishDailyInputGateway;
import br.com.jtech.services.daily.manager.config.infra.annotations.JtechRestController;
import br.com.jtech.services.daily.manager.config.infra.utils.Https;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromFinishRequest;
import static br.com.jtech.services.daily.manager.application.core.domains.daily.Daily.fromRequest;
import static org.springframework.http.ResponseEntity.badRequest;

@JtechRestController("/api/v1/dailies")
@RequiredArgsConstructor
public class FinishDailyController {

    private final FinishDailyInputGateway finishDailyInputGateway;

    @PutMapping("/finish")
    public ResponseEntity<DailyResponse> finish(@RequestBody DailyFinishRequest request) {
        return finishDailyInputGateway.finish(fromFinishRequest(request))
                .map(DailyResponse::fromDomain)
                .map(Https::OK)
                .orElse(badRequest().build());
    }

}
