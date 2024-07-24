package com.ladmakhi.lms.repositories;

import com.ladmakhi.lms.models.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, Long> {
}
