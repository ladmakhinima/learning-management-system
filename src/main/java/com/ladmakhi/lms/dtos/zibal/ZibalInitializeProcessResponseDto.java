package com.ladmakhi.lms.dtos.zibal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ZibalInitializeProcessResponseDto {
    private String message;
    private Long result;
    private Long trackId;
    private String payLink;
}

