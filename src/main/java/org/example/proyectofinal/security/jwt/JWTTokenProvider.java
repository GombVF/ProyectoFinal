package org.example.proyectofinal.security.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.proyectofinal.models.clientes.Cliente;
import org.example.proyectofinal.models.clientes.dtos.ClienteLoginDto;
import org.example.proyectofinal.models.empleados.Empleado;
import org.example.proyectofinal.models.empleados.dtos.EmpleadoLoginDto;
import org.example.proyectofinal.service.ClienteService;
import org.example.proyectofinal.util.enums.TipoPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTTokenProvider {
    private String secret;
    private int jwtExpirationInMs;
    private SecretKey key;
    @Autowired
    private ClienteService clienteService;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateJwtTokenClientes(Authentication authentication, Cliente user) {
        Claims claims;
        if (user.getTipoPersona() == TipoPersona.FISICA){
            claims = Jwts.claims().setSubject("UNAM").setIssuer(user.getPersonaFisica().getRfc()).setAudience("JAVA");;
        }
        else{
            claims = Jwts.claims().setSubject("UNAM").setIssuer(user.getPersonaMoral().getRfc()).setAudience("JAVA");;
        }

        claims.put("principal", authentication.getPrincipal());
        // todo: modificar lógica para el getAthorities obtener Clientes o Empleados
        claims.put("auth", authentication.getAuthorities().stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
            .collect(Collectors.toList()));
        // todo: cambiar al id del usuario?
        //claims.put("issid", user.getUseId());
        //claims.put("issname", user.getUseFirstName() + " " + user.getUseLastName());
        key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000L))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }
    public String generateJwtTokenEmpleados(Authentication authentication, Empleado user) {
        Claims claims = Jwts.claims().setSubject("UNAM").setIssuer(user.getPersonaFisica().getRfc()).setAudience("JAVA");
        claims.put("principal", authentication.getPrincipal());
        // todo: modificar lógica para el getAthorities obtener Clientes o Empleados
        claims.put("auth", authentication.getAuthorities().stream().map(s -> new SimpleGrantedAuthority(s.getAuthority()))
            .collect(Collectors.toList()));
        // todo: cambiar al id del usuario?
        //claims.put("issid", user.getUseId());
        //claims.put("issname", user.getUseFirstName() + " " + user.getUseLastName());
        key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000L))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }

    public Claims getClaims(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getFullName(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        var body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return (String) body.get("issname");
    }

    public String getSubject(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getIssuer(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getIssuer();
    }

    public String getAudience(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getAudience();
    }

    public Date getTokenExpiryFromJWT(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public Date getTokenIatFromJWT(String token) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getIssuedAt();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException exception) {
            log.error("Invalid JWT token -> Message: {}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            log.error("Expired JWT token -> Message: {}", exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Unsupported JWT token -> Message: {}", exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims string is empty -> Message: {}", exception.getMessage());
        }
        return false;
    }

    public long getExpiryDuration() {
        return jwtExpirationInMs * 1000L;
    }
}