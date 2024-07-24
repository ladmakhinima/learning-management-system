package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.transaction.*;
import com.ladmakhi.lms.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TransactionMapper {
    List<GetTransactionDto> mapTransactionsToListOfGetTransactionDto(List<Transaction> transactions);
    List<GetTransactionReportByMonthDto> mapTransactionReportsToListOfGetTransactionReportByMonthDto(List<TransactionReportByMonthDto> transactionReport);
    List<GetTransactionReportBySeasonDto> mapTransactionReportsToListOfGetTransactionReportBySeasonDto(List<TransactionReportBySeasonDto> transactionReport);
    List<GetTransactionReportByDayOfWeekDto> mapTransactionReportsToListOfGetTransactionReportByDayOfWeekDto(List<TransactionReportByDayOfWeekDto> transactionReport);
}
