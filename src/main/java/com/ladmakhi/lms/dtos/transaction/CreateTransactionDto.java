package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.models.Payment;
import com.ladmakhi.lms.models.TransactionGatewayType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTransactionDto {
    private boolean isSuccess;
    private int finalPrice;
    private int finalDiscount;
    private Payment payment;
    private TransactionGatewayType gatewayType;
}
