package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.payment.CreatePaymentDto;
import com.ladmakhi.lms.dtos.payment.InitialPaymentDto;
import com.ladmakhi.lms.dtos.payment.VerifyPaymentDto;
import com.ladmakhi.lms.models.Payment;
import com.ladmakhi.lms.models.User;

import java.util.List;

public interface PaymentService {
    Payment findPaymentByTrackIdAndUserId(Long trackId, Long userId) throws NotFoundException;
    Payment createPayment(CreatePaymentDto dto);
    Payment initialPayment(InitialPaymentDto dto, User user) throws NotFoundException;
    Payment verifyPayment(VerifyPaymentDto dto, User user) throws NotFoundException;
    List<Payment> getPayments();
}
