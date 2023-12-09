package br.com.jtech.services.daily.manager.application.ports.output.squad;

import java.util.UUID;

public interface DeleteSquadOuputGateway {
    void deleteById(UUID id);

    void deleteAll();
}
