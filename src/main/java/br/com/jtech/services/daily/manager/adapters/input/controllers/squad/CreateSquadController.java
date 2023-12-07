package br.com.jtech.services.daily.manager.adapters.input.controllers.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse;
import br.com.jtech.services.daily.manager.application.ports.input.squad.CreateSquadInputGateway;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadResponse.fromDomain;
import static br.com.jtech.services.daily.manager.application.core.domains.squad.Squad.fromRequest;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/squads")
@RequiredArgsConstructor
public class CreateSquadController {

    private final CreateSquadInputGateway createSquadInputGateway;

    @PostMapping
    public ResponseEntity<SquadResponse> create(@RequestBody @Valid SquadRequest request) {
        var domain = createSquadInputGateway.create(fromRequest(request));
        return ResponseEntity.status(CREATED).body(fromDomain(domain));
    }

}
