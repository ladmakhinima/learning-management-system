package com.ladmakhi.lms.implmentations;

import com.auth0.jwt.interfaces.Claim;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.JsonWebTokenService;
import com.twilio.rest.oauth.v1.Authorize;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class JsonWebTokenServiceImpl implements JsonWebTokenService {
    @Value("${secret_key}")
    private String SECRET_KEY;

    @Override
    public String generateToken(User user) {
        String token = Jwts.builder()
                .claim("id", user.getId())
                .setSubject(user.getPhone())
                .setIssuedAt(new Date())
                .setClaims(Collections.singletonMap("role", user.getRole()))
                .setExpiration(getExpirationTime())
                .signWith(generateSignKey())
                .compact();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getPhone(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().toString()))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return token;
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
            String role = String.valueOf(claims.get("role"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    phone,
                    null,
                    List.of(new SimpleGrantedAuthority(role))
            );
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
