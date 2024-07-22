package com.ladmakhi.lms.services;

import com.ladmakhi.lms.dtos.zibal.*;
import com.ladmakhi.lms.models.User;

public interface ZibalPaymentProcess {
    ZibalInitializeProcessResponseDto initializeZibalProcess(ZibalInitializeProcessDto dto, User user);
    ZibalVerifyProcessResponseDto verifyZibalPaymentProcess(ZibalVerifyProcessDto dto, User user);
    ZibalInquiryProcessResponseDto inquiryZibalProcess(ZibalInquiryProcessDto dto, User user);
}
