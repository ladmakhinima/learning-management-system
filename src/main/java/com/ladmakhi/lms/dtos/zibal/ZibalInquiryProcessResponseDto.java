package com.ladmakhi.lms.dtos.zibal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Builder
public class ZibalInquiryProcessResponseDto {
    private String message;
    private Date paidAt;
    private Date verifiedAt;
    private int status;
    private Double amount;
    private Double shaparakFee;
}

