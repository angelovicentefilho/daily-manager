package br.com.jtech.services.daily.manager.config.usecases.employee;

import br.com.jtech.services.daily.manager.adapters.output.employee.CreateEmployeeAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.employee.CreateEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateEmployeeUseCaseConfig {

    @Bean
    public CreateEmployeeUseCase createEmployeeUseCase(CreateEmployeeAdapter createEmployeeAdapter) {
        return new CreateEmployeeUseCase(createEmployeeAdapter);
    }

}
