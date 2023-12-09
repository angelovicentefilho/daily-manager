package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindAllSquadOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomains;

@RestController
@RequestMapping("/api/v1/squads")
@RequiredArgsConstructor
public class FindAllSquadController {

    private final FindAllSquadOutputGateway findAllSquadOutputGateway;

    @RequestMapping
    public ResponseEntity<SquadResponse> findAll() {
        var squads = findAllSquadOutputGateway.findAll();
        return ResponseEntity.ok(fromDomains(squads));
    }
}
