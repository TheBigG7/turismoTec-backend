package com.turismotec.ProyectoGraducacionTurismoTec.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";


    public String getToken(Map<String, Objects> extraClaims, UserDetails user){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000*60*20))
                .signWith(getKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    public String getToken(UserDetails user){
        return getToken(new HashMap<>(),user);
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Obtiene los datos del token
    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Obtenemos el dato del token que queramos, es un metodo generico
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Obtenemos la fecha de expiracion del token
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }

    //Verificamos que el token sea valido
    private boolean istokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    //Obtenemso el usuario del token
    public String getUsernameFormToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    //Verificamos que le token sea valido
    public boolean istokenValid(String token, UserDetails userDetails){
        final String username = getUsernameFormToken(token);
        return(username.equals(userDetails.getUsername()) && !istokenExpired(token));
    }
}

