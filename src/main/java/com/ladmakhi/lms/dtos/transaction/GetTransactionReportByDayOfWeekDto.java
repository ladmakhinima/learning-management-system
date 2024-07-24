package com.ladmakhi.lms.dtos.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTransactionReportByDayOfWeekDto {
    private int dayOfWeek;
    private List<GetTransactionReportDto> reports;
}
