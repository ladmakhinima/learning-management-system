package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.payment.CreatePaymentDto;
import com.ladmakhi.lms.dtos.payment.InitialPaymentDto;
import com.ladmakhi.lms.dtos.payment.VerifyPaymentDto;
import com.ladmakhi.lms.dtos.transaction.CreateTransactionDto;
import com.ladmakhi.lms.dtos.zibal.*;
import com.ladmakhi.lms.models.*;
import com.ladmakhi.lms.repositories.PaymentRepository;
import com.ladmakhi.lms.services.CourseService;
import com.ladmakhi.lms.services.PaymentService;
import com.ladmakhi.lms.services.TransactionService;
import com.ladmakhi.lms.services.ZibalPaymentProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CourseService courseService;
    private final PaymentRepository paymentRepository;
    private final TransactionService transactionService;
    private final ZibalPaymentProcess zibalPaymentProcess;

    @Override
    public Payment findPaymentByTrackIdAndUserId(Long trackId, Long userId) throws NotFoundException {
        return paymentRepository.findByTrackIdAndUserId(trackId, userId)
                .orElseThrow(() -> new NotFoundException("پرداختی یافت نشد"));
    }

    @Override
    public Payment createPayment(CreatePaymentDto dto) {
        var paymentBuilder = Payment.builder()
                .price(dto.getPrice())
                .courses(dto.getCourses())
                .status(PaymentStatus.UN_PAY)
                .expiredAt(new Date(System.currentTimeMillis() + 600000))
                .user(dto.getUser());
        if (!dto.isSuccess()) {
            paymentBuilder.status(PaymentStatus.FAILED_PAY);
        } else {
            paymentBuilder
                    .trackId(dto.getTrackId())
                    .payLink(dto.getPayLink());
        }
        return paymentRepository.save(
                paymentBuilder.build()
        );
    }

    @Override
    public Payment initialPayment(InitialPaymentDto dto, User user) throws NotFoundException {
        List<Course> courses = courseService.getCoursesByIds(dto.getCoursesId());
        int price = courses.stream().map(Course::getPrice)
                .reduce(0, Integer::sum);
        ZibalInitializeProcessResponseDto response = zibalPaymentProcess.initializeZibalProcess(
                ZibalInitializeProcessDto.builder().price(price).build(),
                user
        );
        return createPayment(
                CreatePaymentDto.builder()
                        .isSuccess(response.getMessage().equals("success"))
                        .trackId(response.getTrackId())
                        .payLink(response.getPayLink())
                        .courses(courses)
                        .price(price)
                        .user(user)
                        .build()
        );
    }

    @Override
    public Payment verifyPayment(VerifyPaymentDto dto, User user) throws NotFoundException {
        Payment payment = findPaymentByTrackIdAndUserId(dto.getTrackId(), user.getId());
        if (payment.getStatus() == PaymentStatus.SUCCESS_PAY || payment.getStatus() == PaymentStatus.FAILED_PAY) {
            return payment;
        }
        if (payment.getExpiredAt().before(new Date())) {
            payment.setStatus(PaymentStatus.EXPIRED);
            return paymentRepository.save(payment);
        }
        zibalPaymentProcess.verifyZibalPaymentProcess(
                ZibalVerifyProcessDto.builder()
                        .merchant(dto.getMerchant())
                        .trackId(dto.getTrackId())
                        .build(),
                user
        );
        ZibalInquiryProcessResponseDto inquiryResponse = zibalPaymentProcess.inquiryZibalProcess(
                ZibalInquiryProcessDto.builder()
                        .trackId(dto.getTrackId())
                        .merchant(dto.getMerchant())
                        .build(),
                user
        );
        switch (inquiryResponse.getStatus()) {
            case 3 -> {
                // Status 3 : Failed In Pay
                payment.setStatus(PaymentStatus.FAILED_PAY);
                payment.setVerifiedAt(inquiryResponse.getVerifiedAt());
            }
            case 2, 1 -> {
                // Status 2 | 1 : Success In Pay
                payment.setVerifiedAt(inquiryResponse.getVerifiedAt());
                payment.setStatus(PaymentStatus.SUCCESS_PAY);
                payment.setPayedAt(inquiryResponse.getPaidAt());
                payment.setFee(inquiryResponse.getShaparakFee());
            }
            default -> System.out.println("Nothing Happen On This Transaction");
        }
        if (inquiryResponse.getStatus() >= 1) {
            transactionService.createTransaction(
                    CreateTransactionDto
                            .builder()
                            .payment(payment)
                            .finalDiscount(0)
                            .finalPrice(payment.getPrice())
                            .isSuccess(inquiryResponse.getStatus() == 2 || inquiryResponse.getStatus() == 1)
                            .gatewayType(TransactionGatewayType.ZIBAL)
                            .build()
            );
        }
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
