package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.JsonWebTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JsonWebTokenServiceImpl implements JsonWebTokenService {
    @Value("${secret_key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("id", user.getId())
                .setSubject(user.getPhone())
                .setIssuedAt(new Date())
                .setExpiration(getExpirationTime())
                .signWith(generateSignKey())
                .compact();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(generateSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String phone = String.valueOf(claims.getSubject());
            Authentication authentication = new UsernamePasswordAuthenticationToken(phone, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public SecretKey generateSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public Date getExpirationTime() {
        LocalDate date = LocalDate.now().plus(1, ChronoUnit.DAYS);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
