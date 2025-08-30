package com.yasin.jwtsecutyapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JWTTokenService {
    private final JwtEncoder encoder;
    private final Duration expiry;
    private final String issuer;

    public JWTTokenService(JwtEncoder encoder,
                           @Value("${app.jwt.exp-minutes}") long expMinutes,
                           @Value("${app.jwt.issuer}") String issuer) {
        this.encoder = encoder;
        this.expiry = Duration.ofMinutes(expMinutes);
        this.issuer = issuer;
    }

    public String generateJwtToken(Authentication auth) {
        Instant now = Instant.now();
        Instant exp = now.plus(expiry);
        String scope = auth.getPrincipal().toString();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(this.issuer)
                .issuedAt(now)
                .expiresAt(exp)
                .subject(auth.getName())
                .claim("authorities", auth.getAuthorities())
                .build();
        JwsHeader headers = JwsHeader.with(MacAlgorithm.HS256).type("JWT").build();
        return encoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();
    }
}
