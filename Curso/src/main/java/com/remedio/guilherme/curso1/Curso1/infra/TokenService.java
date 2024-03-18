package com.remedio.guilherme.curso1.Curso1.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.remedio.guilherme.curso1.Curso1.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	
	private String secret;
	
	public String gerarToken(Usuario usuario) {
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    return JWT.create()
		        .withIssuer("Remedio_api")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpireção())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar tolen",exception);
		   
		}
	}
	
	public String getSubjet(String TokenJWT) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
		    return JWT.require(algorithm)
		        .withIssuer("Remedio_api")
		        .build()
		    .verify(TokenJWT)
		    .getSubject();
		        

		} catch (JWTVerificationException exception){
			throw new RuntimeException("Erro inválido ou expirado!");
		}
		
	}

	private Instant dataExpireção() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
