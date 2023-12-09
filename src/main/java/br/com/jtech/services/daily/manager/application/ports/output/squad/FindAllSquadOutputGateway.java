package br.com.jtech.services.daily.manager.application.ports.output.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;

import java.util.List;

public interface FindAllSquadOutputGateway {
    List<Squad> findAll();
}
