package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.txDate >= :startDate and t.custId = :custId")
    List<Transaction> findTransactionsFromLastThreeMonths(@Param("startDate") Date startDate,
                                                          @Param("custId") String custId);
}
