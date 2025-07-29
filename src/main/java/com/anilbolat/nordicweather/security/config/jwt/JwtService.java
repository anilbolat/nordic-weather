package com.anilbolat.nordicweather.security.config.jwt;

import com.anilbolat.nordicweather.security.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.lang.Function;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String getJwtFromRequest(HttpServletRequest req) {
        String bearer = req.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    public String extractEmail(String jwtToken) {
        try {
            return extractClaim(jwtToken, Claims::getSubject);
        } catch (Exception ex) {
            log.warn("Exception occurred while extracting claims.", ex);
            return null;
        }
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        final String email = extractEmail(jwtToken);
        return email != null && email.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
    }

    public String generateToken(User u) {
        return createToken(u.getEmail());
    }

    public boolean isTokenExpired(String jwtToken) {
        Date expirationDate = extractExpiration(jwtToken);
        if (expirationDate != null) {
            return expirationDate.before(new Date());
        }
        return true;
    }

    private String createToken(String email) {
        int expireTimeInMilis = 1000 * 60 * 60 * 10;  // 10h
        
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTimeInMilis))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        //byte [] bytes = Decoders.BASE64.decode(secret);
        byte [] bytes = new SecretKeySpec(secret.getBytes(), Jwts.SIG.HS256.getId()).getEncoded();
        return Keys.hmacShaKeyFor(bytes);
    }

    private Date extractExpiration(String jwtToken) {
        try {
            return extractClaim(jwtToken, Claims::getExpiration);
        } catch (Exception ex) {
            log.warn("Exception occurred while extracting claims.", ex);
            return null;
        }
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) throws JwtException, IllegalArgumentException {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) throws JwtException, IllegalArgumentException {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(jwtToken).getPayload();
    }
}
