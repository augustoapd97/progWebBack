package br.edu.ufabc.ufabfood.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * TokenAuthenticationService
 */
public class TokenAuthenticationService {

    static final long TEMPO_EXPIRACAO = 860_000_000;
    static final String SEGREDO = "Segredo";
	static final String STRING_HEADER = "Authorization";

    static void addAuthentication(HttpServletResponse response, String usuario) {
		String JWT = Jwts.builder()
				.setSubject(usuario)
				.setExpiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
				.signWith(SignatureAlgorithm.HS512, SEGREDO)
				.compact();
		
		response.addHeader(STRING_HEADER, JWT);
		response.addHeader("Username", usuario);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(STRING_HEADER);
		
		if (token != null) {
            
			String usuario = Jwts.parser()
					.setSigningKey(SEGREDO)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			if (usuario != null) {
				return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
			}
		}
		return null;
	}

}