package com.ladmakhi.lms.repositories;

import com.ladmakhi.lms.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTrackIdAndUserId(Long trackId, Long userId);
}
