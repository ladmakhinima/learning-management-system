package com.ladmakhi.lms.dtos.zibal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZibalInitializeApiRequestDto {
    private String merchant;
    private int amount;
    private String callbackUrl;
    private boolean linkToPay;
}


