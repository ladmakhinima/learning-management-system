package com.ladmakhi.lms.configurations;

import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserSecurityConfiguration {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findUserByPhone(username)
                    .orElseThrow(() -> new UsernameNotFoundException("کاربر مورد نظر یافت نشد"));
            List<? extends GrantedAuthority> authorities = new ArrayList<>();
            return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), authorities);
        };
    }
}
