package br.com.user.productsore.usersApi.service;

import br.com.user.productsore.usersApi.domain.user.User;
import br.com.user.productsore.usersApi.exception.TokenGenerationFailedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("userMicrosservice")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);

            return token;
        }
        catch(JWTCreationException exception) {
            throw new TokenGenerationFailedException();
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("userMicrosservice")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTCreationException exception) {
            return "";
        }
    }

    Instant generateExpirationTime() {
        return Instant.now().plusSeconds(3600);
    }
}
