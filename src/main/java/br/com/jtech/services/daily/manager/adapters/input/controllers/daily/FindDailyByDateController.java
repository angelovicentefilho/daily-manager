package br.com.jtech.services.daily.manager.adapters.input.controllers.daily;

import br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindDailyByDateOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.Dates;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.daily.DailyResponse.fromDomains;

@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class FindDailyByDateController {

    private final FindDailyByDateOutputGateway findDailyByDateOutputGateway;


    @GetMapping("/date/{dateTime}")
    public ResponseEntity<DailyResponse> findByDate(@PathVariable String dateTime) {
        var response = findDailyByDateOutputGateway.findByDate(Dates.from(dateTime));
        return ResponseEntity.ok(fromDomains(response));
    }

}
