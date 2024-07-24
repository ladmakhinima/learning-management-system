package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.transaction.*;
import com.ladmakhi.lms.models.Transaction;
import com.ladmakhi.lms.models.TransactionReport;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(CreateTransactionDto dto) throws NotFoundException;
    List<Transaction> getTransactions();
    TransactionReport createTransactionReport(Transaction dto);
    List<TransactionReportByMonthDto> getTransactionReportBasedOnMonth();
    List<TransactionReportBySeasonDto> getTransactionReportBasedOnSeason();
    List<TransactionReportByDayOfWeekDto> getTransactionReportBasedOnDayOfWeek();
}
