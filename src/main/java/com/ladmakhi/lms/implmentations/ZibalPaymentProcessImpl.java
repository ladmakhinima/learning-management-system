package com.ladmakhi.lms.implmentations;


import com.ladmakhi.lms.dtos.zibal.*;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.ZibalPaymentProcess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ZibalPaymentProcessImpl implements ZibalPaymentProcess {
    @Value("${zibal.callbackUrl}")
    private String callbackUrl;
    @Value("${zibal.serverUrl}")
    private String serverUrl;

    @Override
    public ZibalInitializeProcessResponseDto initializeZibalProcess(ZibalInitializeProcessDto dto, User user) {
        ZibalInitializeApiRequestDto httpBody = ZibalInitializeApiRequestDto
                .builder()
                .linkToPay(true)
                .merchant("zibal")
                .callbackUrl(callbackUrl)
                .amount(dto.getPrice())
                .build();
        return WebClient
                .create(serverUrl + "/request")
                .post()
                .bodyValue(httpBody)
                .retrieve()
                .bodyToMono(ZibalInitializeProcessResponseDto.class)
                .block();
    }

    @Override
    public ZibalVerifyProcessResponseDto verifyZibalPaymentProcess(ZibalVerifyProcessDto dto, User user) {
        return WebClient
                .create(serverUrl + "/verify")
                .post()
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ZibalVerifyProcessResponseDto.class)
                .block();
    }

    @Override
    public ZibalInquiryProcessResponseDto inquiryZibalProcess(ZibalInquiryProcessDto dto, User user) {
        return WebClient
                .create(serverUrl + "/inquiry")
                .post()
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(ZibalInquiryProcessResponseDto.class)
                .block();
    }
}
