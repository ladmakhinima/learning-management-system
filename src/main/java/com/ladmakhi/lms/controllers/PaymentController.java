package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.annotation.GetCurrentUser;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.payment.GetInitialPaymentResponseDto;
import com.ladmakhi.lms.dtos.payment.GetPaymentDto;
import com.ladmakhi.lms.dtos.payment.InitialPaymentDto;
import com.ladmakhi.lms.dtos.payment.VerifyPaymentDto;
import com.ladmakhi.lms.mappers.PaymentMapper;
import com.ladmakhi.lms.models.Payment;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping("/initial")
    public ResponseEntity<GetInitialPaymentResponseDto> initialPayment(
            @RequestBody @Valid InitialPaymentDto dto,
            @GetCurrentUser User user
    ) throws NotFoundException {
        Payment payment = paymentService.initialPayment(dto, user);
        GetInitialPaymentResponseDto responseDto = paymentMapper.mapPaymentToGetInitialPaymentResponseDto(payment);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<GetPaymentDto> verifyPayment(
            @RequestBody @Valid VerifyPaymentDto dto,
            @GetCurrentUser User user
    ) throws NotFoundException {
        Payment payment = paymentService.verifyPayment(dto, user);
        GetPaymentDto responseDto = paymentMapper.mapPaymentToGetPaymentDto(payment);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GetPaymentDto>> getPayments() {
        List<Payment> payments = paymentService.getPayments();
        List<GetPaymentDto> responseDto = paymentMapper.mapPaymentsToListOfGetPaymentDto(payments);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
