package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.models.TransactionReport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TransactionReportByMonthDto {
    private List<TransactionReport> reports;
    private int month;
}
