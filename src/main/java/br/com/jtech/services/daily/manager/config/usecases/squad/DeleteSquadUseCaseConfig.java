package br.com.jtech.services.daily.manager.config.usecases.squad;

import br.com.jtech.services.daily.manager.adapters.output.squad.DeleteSquadAdapter;
import br.com.jtech.services.daily.manager.adapters.output.squad.FindSquadByIdAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.squad.DeleteSquadUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteSquadUseCaseConfig {

    @Bean
    public DeleteSquadUseCase deleteSquadUseCase(DeleteSquadAdapter deleteSquadAdapter,
                                                 FindSquadByIdAdapter findSquadByIdAdapter) {
        return new DeleteSquadUseCase(deleteSquadAdapter, findSquadByIdAdapter);
    }

}
