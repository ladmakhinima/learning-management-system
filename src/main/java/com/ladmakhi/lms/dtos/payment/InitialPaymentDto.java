package com.ladmakhi.lms.dtos.payment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InitialPaymentDto {
    @NotNull
    private List<Long> coursesId;
}
