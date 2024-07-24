package com.ladmakhi.lms.dtos.transaction;

import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.SeasonType;
import com.ladmakhi.lms.models.TransactionGatewayType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetTransactionReportDto {
    private Long id;
    private int year;
    private int month;
    private SeasonType seasonType;
    private Date time;
    private GetUserDto user;
    private TransactionGatewayType gateway;
    private int finalPrice;
    private int finalDiscount;
    private Date submitAt;
    private int dayOfWeek;
}
