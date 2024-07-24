package com.ladmakhi.lms.dtos.payment;

import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CreatePaymentDto {
    private int price;
    private List<Course> courses;
    private User user;
    private boolean isSuccess;
    private Long trackId;
    private String payLink;
}
