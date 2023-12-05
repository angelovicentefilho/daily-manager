package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyBySquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse.fromDomains;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class FindDailyBySquadController {

    public FindDailyBySquadOutputGateway findDailyBySquadOutputGateway;

    @GetMapping("/squad/{squad}")
    public ResponseEntity<DailyResponse> findBySquad(@PathVariable String squad) {
        var domain = findDailyBySquadOutputGateway.findBySquad(squad);
        return ok(fromDomains(domain));
    }

}
