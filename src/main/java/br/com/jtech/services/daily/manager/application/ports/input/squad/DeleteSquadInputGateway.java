package br.com.jtech.services.daily.manager.application.ports.input.squad;

public interface DeleteSquadInputGateway {

    void deleteById(String id);

    void deleteAll();
}
