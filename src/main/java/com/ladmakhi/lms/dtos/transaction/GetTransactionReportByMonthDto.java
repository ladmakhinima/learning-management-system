package com.ladmakhi.lms.dtos.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTransactionReportByMonthDto {
    private int month;
    private List<GetTransactionReportDto> reports;
}
