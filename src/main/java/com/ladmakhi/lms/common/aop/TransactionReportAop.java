package com.ladmakhi.lms.common.aop;

import com.ladmakhi.lms.models.Transaction;
import com.ladmakhi.lms.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TransactionReportAop {
    private final TransactionService transactionService;

    @AfterReturning(value = "execution(* com.ladmakhi.lms.services.TransactionService.createTransaction(..))", returning = "transaction")

    public void afterSuccessTransaction(Transaction transaction) {
        if (transaction.isSuccess()) {
            transactionService.createTransactionReport(transaction);
        }
    }
}
