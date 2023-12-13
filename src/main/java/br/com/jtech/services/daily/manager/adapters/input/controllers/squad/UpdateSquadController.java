package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.application.ports.input.squad.UpdateSquadInputGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomain;
import static br.com.jtech.services.daily.manager.application.core.domains.squad.Squad.fromRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/squads")
@RequiredArgsConstructor
public class UpdateSquadController {

    private final UpdateSquadInputGateway updateSquadInputGateway;

    @PutMapping("/{id}")
    public ResponseEntity<SquadResponse> update(@PathVariable String id, @RequestBody @Valid SquadRequest request) {
        request.setId(id);
        return updateSquadInputGateway.update(fromRequest(request))
                .map(squad -> ok(fromDomain(squad)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
