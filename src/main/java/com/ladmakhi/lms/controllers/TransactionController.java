package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.annotation.CheckRole;
import com.ladmakhi.lms.dtos.transaction.GetTransactionDto;
import com.ladmakhi.lms.mappers.TransactionMapper;
import com.ladmakhi.lms.models.Transaction;
import com.ladmakhi.lms.models.UserRole;
import com.ladmakhi.lms.services.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    @CheckRole(role = UserRole.Admin)
    public ResponseEntity<List<GetTransactionDto>> getTransactions() {
        List<Transaction> transactions = transactionService.getTransactions();
        List<GetTransactionDto> responseDto = transactionMapper.mapTransactionsToListOfGetTransactionDto(transactions);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
