package br.com.jtech.services.daily.manager.config.usecases.squad;

import br.com.jtech.services.daily.manager.adapters.output.squad.CreateSquadAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.squad.CreateSquadUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateSquadUseCaseConfig {

    @Bean
    public CreateSquadUseCase createSquadUseCase(CreateSquadAdapter createSquadAdapter) {
        return new CreateSquadUseCase(createSquadAdapter);
    }

}
