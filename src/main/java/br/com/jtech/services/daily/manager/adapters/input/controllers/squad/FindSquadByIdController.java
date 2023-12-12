package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.application.ports.input.squad.FindSquadByIdInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomain;

@RestController
@RequestMapping("/api/v1/squads")
@RequiredArgsConstructor
public class FindSquadByIdController {

    private final FindSquadByIdInputGateway findSquadByIdInputGateway;

    @RequestMapping("/{squadId}")
    public ResponseEntity<SquadResponse> findById(@PathVariable String squadId) {
        var squad = findSquadByIdInputGateway.findById(squadId);
        return ResponseEntity.ok(fromDomain(squad));
    }
}
