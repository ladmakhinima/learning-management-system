package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.models.TransactionGatewayType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTransactionDto {
    private Long id;
    private boolean isSuccess;
    private Double finalPrice;
    private Double finalDiscount;
    private TransactionGatewayType transactionGatewayType;
    private GetTransactionPaymentDto payment;
}
