package br.com.pizzaplanet.util;

import br.com.pizzaplanet.api.repository.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtUtil {

  @Value("${security.secret-key}")
  private String secret;
  @Value("${security.issuer}")
  private String issuer;

  public String generateToken(User user) {
    try {
      var algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer(issuer)
          .withSubject(String.valueOf(user.getId()))
          .withExpiresAt(expirationDate())
          .withClaim("id", String.valueOf(user.getId()))
          .withClaim("nome", user.getUsername())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException("erro ao gerrar token jwt", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      return JWT.require(algoritmo)
          .withIssuer(issuer)
          .build()
          .verify(tokenJWT)
          .getSubject();
    } catch (JWTVerificationException exception) {
      throw new RuntimeException("Token JWT inválido ou expirado!");
    }
  }

  private Instant expirationDate() {
    return LocalDateTime.now().plusYears(10).toInstant(ZoneOffset.of("-03:00"));
  }
}
