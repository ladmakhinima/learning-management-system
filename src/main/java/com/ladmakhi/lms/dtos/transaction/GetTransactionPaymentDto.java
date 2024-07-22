package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetTransactionPaymentDto {
    private Long id;
    private GetUserDto user;
    private PaymentStatus status;
    private Double price;
    private Double fee;
    private Date payedAt;
    private Date expiredAt;
    private Date verifiedAt;
}
