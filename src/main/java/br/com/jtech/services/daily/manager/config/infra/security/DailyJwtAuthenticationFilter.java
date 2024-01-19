package br.com.jtech.services.daily.manager.config.infra.security;

import br.com.jtech.services.daily.manager.config.infra.adapter.LocalDateTimeAdapter;
import br.com.jtech.services.daily.manager.config.infra.exceptions.Error;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class DailyJwtAuthenticationFilter extends OncePerRequestFilter {

    private final DailyJwtService dailyJwtService;
    private final UserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final var header = request.getHeader("Authorization");
            final String jwt;
            final String email;
            if (isNotAuth(header)) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = header.substring(7);
            email = dailyJwtService.getEmailFromToken(jwt);
            if (readyForLogin(email)) {
                var userDetails = userDetailsService.loadUserByUsername(email);
                if (dailyJwtService.validateToken(jwt, email)) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    throw new IllegalArgumentException("Invalid token " + HttpStatus.UNAUTHORIZED);
                }
            } else {
                throw new IllegalArgumentException("Missing valid token " + HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            sendError(request, response, e.getMessage());
        }


    }


    @SneakyThrows
    private void sendError(HttpServletRequest request, HttpServletResponse response, String message) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(new Error(HttpStatus.PRECONDITION_FAILED, message, request.getContextPath(),
                LocalDateTime.now())));
    }

    private boolean readyForLogin(String email) {
        return nonNull(email) && isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    private boolean isNotAuth(String header) {
        return header == null || !header.startsWith("Bearer ");
    }
}
