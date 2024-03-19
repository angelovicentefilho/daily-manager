package br.com.jtech.services.daily.manager.config.infra.security;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import static br.com.jtech.services.daily.manager.config.infra.utils.GenSecretKey.getSecretKey;

@Service
public class DailyJwtService {

    @Value("${address.city:SPHpCMTIVRB5HpQDLujUyd/h/h3TOvtXOU0AZUbdQ7c=}")
    private String secret;

    @Value("${address.distance:90000000}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        SecretKey key = getSecretKey(secret);

        var userPrincipal = (DailyUserDetails) authentication.getPrincipal();


        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("roles", getRoles(userPrincipal))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public Object getRoles(UserDetails userPrincipal) {
        var claims = Jwts.claims().subject(userPrincipal.getUsername());
        return claims.build().put("roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }


    public String generateToken(Employee employee) {
        SecretKey key = getSecretKey(secret);


        return Jwts.builder()
                .subject(employee.getEmail())
//                .claim("roles", getRoles(new DailyUserDetails(employee.toDocument())))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, String email) {
        final String emailFromToken = getEmailFromToken(token);
        return (emailFromToken.equals(email) && !isTokenExpired(token));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getSecretKey(secret)).build().parseSignedClaims(token).getPayload();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
