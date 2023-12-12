package br.com.jtech.services.daily.manager.config.usecases.squad;

import br.com.jtech.services.daily.manager.adapters.output.squad.FindSquadByIdAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.squad.FindSquadByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindSquadByIdUseCaseConfig {

    @Bean
    public FindSquadByIdUseCase findSquadByIdUseCase(FindSquadByIdAdapter findSquadByIdAdapter) {
        return new FindSquadByIdUseCase(findSquadByIdAdapter);
    }
}
