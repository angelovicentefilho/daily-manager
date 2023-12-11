package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindAllSquadOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomain;
import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomains;

@RestController
@RequestMapping("/api/v1/squads")
@RequiredArgsConstructor
public class FindSquadByIdController {

    private final FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @RequestMapping("/{squadId}")
    public ResponseEntity<SquadResponse> findById(@PathVariable UUID squadId) {
        var squad = findSquadByIdOutputGateway.findById(squadId);
        return ResponseEntity.ok(fromDomain(squad));
    }
}
