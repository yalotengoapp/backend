package com.ybytu.yalotengo.security.jwt;

import com.ybytu.yalotengo.security.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private final String JWT_SECRET_KEY = "mySecretKeyForJWTTokenGenerationThatIsAtLeast256BitsLong";
    private final Long JWT_EXPIRATION = 1800000L;

    public String generateToken(UserDetail userDetail) {
        return buildToken(userDetail, JWT_EXPIRATION);
    }

    private String buildToken(UserDetail userDetail, long jwtExpiration) {
        return Jwts
                .builder()
                .claim("role", userDetail.getAuthorities().toString())
                .subject(userDetail.getUsername())
                .issuedAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSignKey())
                .compact();
    }

    public String extractUsername (String token) {
        return extractAllClams(token).getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            extractAllClams(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    private Claims extractAllClams(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignKey() {
        byte[] bytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
