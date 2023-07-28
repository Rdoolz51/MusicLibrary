package com.karendiscord.security;

import com.karendiscord.models.User;
import com.karendiscord.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenGenerator {

    private final SecretKey secretKey;
    private final UserRepository userRepository;
    @Autowired
    public TokenGenerator(@Value("${JWT_KEY}") String key, UserRepository userRepository) {
        this.secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
        this.userRepository = userRepository;
    }
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Instant issued = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issued.plus(60, ChronoUnit.MINUTES);
        User user = userRepository.findByUsername(username).orElseThrow();

        return Jwts.builder()
                .setSubject(username)
                .claim("id", user.getId())
                .claim("first_name", user.getFirstName())
                .claim("last_name", user.getLastName())
                .setIssuedAt(Date.from(issued))
                .setExpiration(Date.from(expiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS512))
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch(Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Invalid/Expired Token");
        }
    }

    public Claims getClaims(String token) {
        if(token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
