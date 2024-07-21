package com.ladmakhi.lms.configurations;

import com.ladmakhi.lms.common.resolver.GetCurrentUserResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CustomAnnotationConfiguration implements WebMvcConfigurer {
    private final GetCurrentUserResolver getCurrentUserResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getCurrentUserResolver);
    }
}
