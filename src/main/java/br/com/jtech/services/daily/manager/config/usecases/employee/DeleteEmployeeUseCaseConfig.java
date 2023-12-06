package br.com.jtech.services.daily.manager.config.usecases.employee;

import br.com.jtech.services.daily.manager.adapters.output.employee.DeleteEmployeeAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByEmailAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByIdAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByUsernameAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.employee.DeleteEmployeeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteEmployeeUseCaseConfig {

    @Bean
    public DeleteEmployeeUseCase deleteEmployeeUseCase(DeleteEmployeeAdapter deleteEmployeeAdapter,
                                                       FindEmployeeByEmailAdapter findEmployeeByEmailAdapter,
                                                       FindEmployeeByUsernameAdapter findEmployeeByUsernameAdapter,
                                                       FindEmployeeByIdAdapter findEmployeeByIdAdapter) {
        return new DeleteEmployeeUseCase(deleteEmployeeAdapter, findEmployeeByEmailAdapter, findEmployeeByUsernameAdapter, findEmployeeByIdAdapter);
    }

}
