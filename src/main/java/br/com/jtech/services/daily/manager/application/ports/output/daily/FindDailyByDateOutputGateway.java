package br.com.jtech.services.daily.manager.application.ports.output.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;

import java.time.LocalDateTime;
import java.util.List;

public interface FindDailyByDateOutputGateway {
    List<Daily> findByDate(LocalDateTime dateTime);
}
