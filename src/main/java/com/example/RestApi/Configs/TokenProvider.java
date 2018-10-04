package com.example.RestApi.Configs;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private String jwtSecret = "something";
    Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    public String generateJwtToken(Authentication authentication) throws UnsupportedEncodingException {

        Date d = new Date();
        Date expiry = new Date(System.currentTimeMillis()+ (1000 * 120));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        final String authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(d)
                .claim("roles", authorities)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    public String getUserNameFromJwtToken(String token) {


        String userName = null;
        try {
            userName = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return userName;
    }

    public boolean validateJwtToken(String authToken) throws UnsupportedEncodingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.debug("signature exception"+e);
        } catch (MalformedJwtException e) {
           logger.debug("token malformed"+e);

        } catch (ExpiredJwtException e) {
           logger.debug("token expired"+e);

        } catch (UnsupportedJwtException e) {
            logger.debug("unsupported"+e);

        } catch (IllegalArgumentException e) {
            logger.debug("Illegal"+e);

        }

        return false;
    }

}
