package com.ladmakhi.lms.common.aop;

import com.ladmakhi.lms.common.annotation.CheckRole;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CheckRoleAop {
    @Before("@annotation(checkRole)")
    public void checkUserRole(JoinPoint joinPoint, CheckRole checkRole) throws Exception {
        List<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList();
        boolean hasAccess = roles.stream().anyMatch(e -> {
            return e.equals(new SimpleGrantedAuthority(checkRole.role().toString()));
        });
        if (!hasAccess) {
            throw new Exception("خطای دسترسی");
        }
    }
}
