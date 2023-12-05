package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyBySquadAndDateOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.Dates;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse.fromDomain;

@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class FindDailyBySquadAndDateController {

    private final FindDailyBySquadAndDateOutputGateway findDailyBySquadAndDateOutputGateway;


    @GetMapping("/{squad}/{dateTime}")
    public ResponseEntity<DailyResponse> findBySquadAndDate(@PathVariable String squad, @PathVariable String dateTime) {
        var domain = findDailyBySquadAndDateOutputGateway.findBySquadAndDate(squad, Dates.from(dateTime));
        return ResponseEntity.ok(fromDomain(domain));
    }

}
