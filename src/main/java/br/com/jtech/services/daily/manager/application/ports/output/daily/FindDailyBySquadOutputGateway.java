package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

import java.util.List;

public interface FindDailyBySquadOutputGateway {
    List<Daily> findBySquad(String squad);
}
