package com.ladmakhi.lms.common.resolver;

import com.ladmakhi.lms.common.annotation.GetCurrentUser;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class GetCurrentUserResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(GetCurrentUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String phone = authentication.getName();
        User user = userService.findUserByPhone(phone);
        return user;
    }
}
