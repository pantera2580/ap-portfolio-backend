package com.mec.apportfoliobackend.security.jwt;

import com.mec.apportfoliobackend.security.auth.Auth;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private final String secret=System.getenv("JWT_KEY");
    private final int expiration=3600*1000;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        Auth userAuth = (Auth) authentication.getPrincipal();
        Date date = new Date();
        return Jwts.builder()
                .setSubject(userAuth.getUsername())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expiration))
                .signWith(this.getSigningKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.getSigningKey()).build().parseClaimsJws(token);
            return true;
        }

        catch (MalformedJwtException e) {
            logger.error("Malformed token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        } catch (ExpiredJwtException e) {
            logger.error("Expired token");
        } catch (IllegalArgumentException e) {
            logger.error("Illegal token");
        } catch (SignatureException e) {
            logger.error("Fail signature");
        }
        return false;
    }
}
