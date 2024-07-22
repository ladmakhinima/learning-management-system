package com.ladmakhi.lms.dtos.zibal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZibalInquiryProcessDto {
    private Long trackId;
    private String merchant;
}
