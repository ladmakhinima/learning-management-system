package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.models.TransactionGatewayType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetTransactionDto {
    private Long id;
    private boolean isSuccess;
    private int finalPrice;
    private int finalDiscount;
    private TransactionGatewayType transactionGatewayType;
    private GetTransactionPaymentDto payment;
    private Date createdAt;
    private Date updatedAt;
}
