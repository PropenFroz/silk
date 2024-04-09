package com.a03.silk.security.jwt;

import com.a03.silk.model.UserModel;
import com.a03.silk.repository.UserDb;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${silk.app.jwtSecret}")
    private String jwtSecret;

    @Value("${silk.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private UserDb userDb;

    public String generateJwtToken(String username, Authentication authentication) {

        UserDetails user = (UserDetails) authentication.getPrincipal();

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration (new Date((new Date()).getTime() + jwtExpirationMs)) .signWith(SignatureAlgorithm. HS512, jwtSecret)
                .claim("role", roles)
                .claim("userId", getIdFromUsername(user.getUsername())) // Menambahkan klaim ID pengguna
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Long getIdFromUsername(String username) {
        return userDb.findByUsername(username).getId();
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public String setExpiration(Date expirationDate, String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
