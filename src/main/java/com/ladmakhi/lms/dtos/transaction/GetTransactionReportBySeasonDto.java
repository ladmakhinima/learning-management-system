package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.models.SeasonType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTransactionReportBySeasonDto {
    private SeasonType season;
    private List<GetTransactionReportDto> reports;
}

