package br.com.jtech.services.daily.manager.config.usecases.employee;

import br.com.jtech.services.daily.manager.adapters.output.employee.UpdateEmployeeAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.employee.UpdateEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateEmployeeUseCaseConfig {

    @Bean
    public UpdateEmployeeUseCase updateEmployeeUseCase(UpdateEmployeeAdapter updateEmployeeAdapter) {
        return new UpdateEmployeeUseCase(updateEmployeeAdapter);
    }

}
