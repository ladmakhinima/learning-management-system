package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.dtos.transaction.CreateTransactionDto;
import com.ladmakhi.lms.models.Transaction;
import com.ladmakhi.lms.repositories.TransactionRepository;
import com.ladmakhi.lms.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(CreateTransactionDto dto) {
        return transactionRepository.save(
                Transaction.builder()
                        .finalDiscount(dto.getFinalDiscount())
                        .finalPrice(dto.getFinalPrice())
                        .transactionGatewayType(dto.getGatewayType())
                        .isSuccess(dto.isSuccess())
                        .payment(dto.getPayment())
                        .build()
        );
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
