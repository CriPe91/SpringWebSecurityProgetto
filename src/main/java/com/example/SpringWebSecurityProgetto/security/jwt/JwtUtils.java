package com.example.SpringWebSecurityProgetto.security.jwt;

import com.example.SpringWebSecurityProgetto.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    // Agganciare le costanti legate al JWT
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirations;

    // Creazione del JWT
    public String creaJwtToken(Authentication autenticazione) {

        // Recupero il dettaglio principal (username)
        UserDetailsImpl utentePrincipal = (UserDetailsImpl) autenticazione.getPrincipal();

        // Creazione del JWT
        return Jwts.builder()
                .setSubject(utentePrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirations))
                .signWith(recuperoChiave(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Recupera l'username dal JWT
    public String recuperoUsernameDaToken(String token) {
        return Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parseClaimsJwt(token).getBody().getSubject();
    }

    // Recupera la scadenza dal JWT
    public Date recuperoScadenzaDaToken(String token) {
        return Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parseClaimsJwt(token).getBody().getExpiration();
    }

    // Validazione del TOKEN JWT
    public boolean validazioneJwtToken(String token) {
        Jwts.parserBuilder().setSigningKey(recuperoChiave()).build().parse(token);
        return true;
    }

    // Recupero della chiave
    public Key recuperoChiave() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
