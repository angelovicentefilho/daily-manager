package br.com.jtech.services.daily.manager.config.usecases.authentication;

import br.com.jtech.services.daily.manager.adapters.output.authentication.AuthenticationManagerAdapter;
import br.com.jtech.services.daily.manager.adapters.output.authentication.GetJwtTokenAdapter;
import br.com.jtech.services.daily.manager.adapters.output.employee.FindEmployeeByEmailAdapter;
import br.com.jtech.services.daily.manager.application.core.usecases.authentication.AuthenticationUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationUseCaseConfig {

    @Bean
    public AuthenticationUseCase authenticationUseCase(final AuthenticationManagerAdapter authenticationManagerAdapter,
                                                       final FindEmployeeByEmailAdapter findEmployeeByEmailAdapter,
                                                       final GetJwtTokenAdapter getJwtTokenAdapter) {
        return new AuthenticationUseCase(authenticationManagerAdapter, findEmployeeByEmailAdapter, getJwtTokenAdapter);
    }

}
