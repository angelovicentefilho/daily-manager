package br.com.jtech.services.daily.manager.config.usecases.squad;

import br.com.jtech.services.daily.manager.adapters.output.employee.UpdateEmployeeAdapter;
import br.com.jtech.services.daily.manager.adapters.output.squad.UpdateSquadAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.employee.UpdateEmployeeUseCase;
import br.com.jtech.services.daily.manager.application.core.usecases.squad.UpdateSquadUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateSquadUseCaseConfig {

    @Bean
    public UpdateSquadUseCase updateSquadUseCase(UpdateSquadAdapter updateSquadAdapter) {
        return new UpdateSquadUseCase(updateSquadAdapter);
    }

}
