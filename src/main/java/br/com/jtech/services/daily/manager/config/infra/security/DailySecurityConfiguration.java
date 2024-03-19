package br.com.jtech.services.daily.manager.config.infra.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DailySecurityConfiguration {

    private final DailyJwtAuthenticationFilter filter;
    private final AuthenticationProvider provider;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/auth/**", "/swagger-ui/**", "/swagger-ui.html/**", "/swagger-ui.html", "/swagger-ui.html","swagger-ui",
                                "/swagger-ui/**", "/api/v3/api-docs/**", "/health", "/swagger-ui.html",
                                "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui/index.html")
                        .permitAll()
                        .requestMatchers("/api/v1/employees/**").hasAnyAuthority("ADMIN", "LEAD")
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
