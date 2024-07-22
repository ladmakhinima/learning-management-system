package com.ladmakhi.lms.models;

import com.ladmakhi.lms.common.entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends CoreEntity {
    private boolean isSuccess;

    private Double finalPrice;

    private Double finalDiscount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    private TransactionGatewayType transactionGatewayType;
}
