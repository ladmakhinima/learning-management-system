package com.ladmakhi.lms.dtos.payment;

import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetPaymentDto {
    private Long id;
    private PaymentStatus status;
    private Double price;
    private Long trackId;
    private Double fee;
    private Date payedAt;
    private Date verifiedAt;
    private GetUserDto user;
}
