package com.Arteria.ArteriaBackend.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String username) {
        // Este método genera un token JWT para un nombre de usuario dado.
        // Establece el "subject" (el usuario), la fecha de emisión, la fecha de expiración
        // y lo firma con la clave secreta usando el algoritmo HS256.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Se establece que el token es valido por 10 horas
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();// Construye y serializa el JWT a una cadena compacta
    }

    public String extractUsername(String token) {
        // Extrae el nombre de usuario (subject) del token JWT.
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Un método genérico para extraer cualquier "claim" (información) del token.
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Parsea el token JWT y obtiene todos sus "claims" (carga útil) -  Utiliza la clave secreta para verificar la firma del token.
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        // Verifica si el token JWT ha expirado comparando su fecha de expiración con la fecha actual.
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        // Extrae la fecha de expiración del token JWT.
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        // Valida un token JWT.
        // 1. Extrae el nombre de usuario del token.
        final String username = extractUsername(token);
        // 2. Compara el nombre de usuario extraído con el nombre de usuario de los UserDetails:
        // Esto verifica que el token fue emitido para el usuario que actualmente se está autenticando/autorizando.
        // 3. Verifica si el token no ha expirado: Esto asegura que el token aún es válido dentro de su período de vida.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
