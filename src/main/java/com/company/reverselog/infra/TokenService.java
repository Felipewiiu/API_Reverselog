package com.company.reverselog.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.company.reverselog.domain.usuario.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                    .withIssuer("API_Reverselog")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiresAt())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw  new RuntimeException("Error ao gerar o token JWT", exception);
        }
    }

    private Instant dataExpiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
