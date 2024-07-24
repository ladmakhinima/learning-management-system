package com.ladmakhi.lms.dtos.zibal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ZibalVerifyProcessResponseDto {
    private String message;
    private int result;
    private String paidAt;
    private int status;
    private int amount;
}
