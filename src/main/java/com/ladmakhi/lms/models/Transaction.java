package com.ladmakhi.lms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private int finalPrice;

    private int finalDiscount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    private TransactionGatewayType transactionGatewayType;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "transaction")
    @JsonBackReference
    private TransactionReport report;
}
