package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.util.SeasonUtil;
import com.ladmakhi.lms.dtos.transaction.*;
import com.ladmakhi.lms.models.SeasonType;
import com.ladmakhi.lms.models.Transaction;
import com.ladmakhi.lms.models.TransactionReport;
import com.ladmakhi.lms.repositories.TransactionReportRepository;
import com.ladmakhi.lms.repositories.TransactionRepository;
import com.ladmakhi.lms.services.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionReportRepository transactionReportRepository;
    private final SeasonUtil seasonUtil;

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

    @Override
    @Transactional
    public TransactionReport createTransactionReport(Transaction dto) {
        Date date = dto.getPayment().getCreatedAt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        TransactionReport transactionReport = TransactionReport.builder()
                .courses(dto.getPayment().getCourses().stream().toList())
                .finalDiscount(dto.getFinalDiscount())
                .finalPrice(dto.getFinalPrice())
                .user(dto.getPayment().getUser())
                .year(calendar.get(Calendar.YEAR))
                .month(calendar.get(Calendar.MONTH))
                .season(seasonUtil.getSeasonBasedOnDate(date))
                .time(date)
                .dayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))
                .gateway(dto.getTransactionGatewayType())
                .submitAt(dto.getCreatedAt())
                .transaction(dto)
                .build();
        return transactionReportRepository.save(
                transactionReport
        );
    }

    @Override
    public List<TransactionReportByMonthDto> getTransactionReportBasedOnMonth() {
        Map<Integer, List<TransactionReport>> reports = transactionReportRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(TransactionReport::getMonth));

        List<TransactionReportByMonthDto> mappedReports = new ArrayList<>();
        List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
                .forEach(month -> mappedReports.add(
                        TransactionReportByMonthDto
                                .builder()
                                .month(month)
                                .reports(reports.getOrDefault(month, List.of()))
                                .build()
                ));
        return mappedReports;
    }

    @Override
    public List<TransactionReportBySeasonDto> getTransactionReportBasedOnSeason() {
        Map<SeasonType, List<TransactionReport>> reports = transactionReportRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(TransactionReport::getSeason));
        List<TransactionReportBySeasonDto> mappedReports = new ArrayList<>();
        List.of(SeasonType.SPRING, SeasonType.SUMMER, SeasonType.FALL, SeasonType.WINTER)
                .forEach(season -> mappedReports.add(
                        TransactionReportBySeasonDto
                                .builder()
                                .season(season)
                                .reports(reports.getOrDefault(season, List.of()))
                                .build()
                ));
        return mappedReports;
    }

    @Override
    public List<TransactionReportByDayOfWeekDto> getTransactionReportBasedOnDayOfWeek() {
        Map<Integer, List<TransactionReport>> reports = transactionReportRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(TransactionReport::getDayOfWeek));
        List<TransactionReportByDayOfWeekDto> mappedReports = new ArrayList<>();
        List<Integer> weekdays = List.of(1, 2, 3, 4, 5, 6, 7);
        weekdays.forEach(weekday -> mappedReports.add(
                TransactionReportByDayOfWeekDto
                        .builder()
                        .reports(reports.getOrDefault(weekday, List.of()))
                        .dayOfWeek(weekday)
                        .build()
        ));
        return mappedReports;
    }
}

