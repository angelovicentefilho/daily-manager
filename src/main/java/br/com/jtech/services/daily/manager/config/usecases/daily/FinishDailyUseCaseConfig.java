package br.com.jtech.services.daily.manager.config.usecases.daily;

import br.com.jtech.services.daily.manager.adapters.output.daily.FinishDailyAdapter;
import br.com.jtech.services.daily.manager.adapters.output.daily.SendEmailAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByEmailAdapter;
import br.com.jtech.services.daily.manager.adapters.output.squad.FindSquadByIdAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.daily.FinishDailyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinishDailyUseCaseConfig {

    @Bean
    public FinishDailyUseCase useCase(FinishDailyAdapter finishDailyAdapter,
                                      FindEmployeeByEmailAdapter findEmployeeByEmailAdapter,
                                      FindSquadByIdAdapter findSquadByIdAdapter,
                                      SendEmailAdapter sendEmailAdapter) {
        return new FinishDailyUseCase(finishDailyAdapter, findEmployeeByEmailAdapter, findSquadByIdAdapter, sendEmailAdapter);
    }
}
