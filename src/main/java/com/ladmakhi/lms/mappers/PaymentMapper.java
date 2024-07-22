package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.payment.GetInitialPaymentResponseDto;
import com.ladmakhi.lms.dtos.payment.GetPaymentDto;
import com.ladmakhi.lms.models.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    GetInitialPaymentResponseDto mapPaymentToGetInitialPaymentResponseDto(Payment payment);

    GetPaymentDto mapPaymentToGetPaymentDto(Payment payment);

    List<GetPaymentDto> mapPaymentsToListOfGetPaymentDto(List<Payment> payments);
}
