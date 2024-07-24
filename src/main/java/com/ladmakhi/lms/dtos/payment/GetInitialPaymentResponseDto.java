package com.ladmakhi.lms.dtos.payment;

import com.ladmakhi.lms.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInitialPaymentResponseDto {
    private Long id;
    private PaymentStatus status;
    private int price;
    private Long trackId;
    private String payLink;
}
