package com.ladmakhi.lms.common.filter;

import com.ladmakhi.lms.services.JsonWebTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JsonWebTokenService jwtService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    )
            throws ServletException, IOException {
        String headerToken = request.getHeader("Authorization");
        String token = headerToken == null ? null : headerToken.split("Bearer ")[1];
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtService.verifyToken(token);
        filterChain.doFilter(request, response);
        return;
    }
}
