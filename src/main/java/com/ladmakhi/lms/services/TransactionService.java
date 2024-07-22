package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.transaction.CreateTransactionDto;
import com.ladmakhi.lms.models.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(CreateTransactionDto dto) throws NotFoundException;
    List<Transaction> getTransactions();
}
