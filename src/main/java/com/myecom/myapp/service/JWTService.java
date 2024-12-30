package com.myecom.myapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.myecom.myapp.domain.dto.user.UserDto;
import com.myecom.myapp.domain.entity.user.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.time.Instant;

@Service
public class JWTService {

    public static final String USERNAME = "USERNAME";
    @Value("${jwt.algorithm.key}")
    private String algoKey;

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private Long expirySeconds;

    private Algorithm algorithm;


    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(algoKey);
    }

    public String generateJWTToken(User user) {
        return JWT.create()
                .withClaim(USERNAME, user.getUserName())
                .withExpiresAt(Instant.now().plusSeconds(expirySeconds))
                .withIssuer(issuer)
                .sign(algorithm);

    }

    public String getUserName(String token) {
        return JWT.decode(token).getClaim(USERNAME).asString();
    }
}
