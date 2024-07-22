package com.ladmakhi.lms.dtos.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyPaymentDto {
    private Long trackId;
    private String merchant;
}
