package com.evaluation.demo.service;

import com.evaluation.demo.repository.TransactionRepository;
import com.evaluation.demo.entity.Transaction;
import com.evaluation.demo.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Get Last three months transaction of customer from database
     *
     * @param custId
     * @return
     */
    public List<Transaction> getTransactionsFromLastThreeMonths(String custId) {
        Date startDate = DateUtils.getStartDateOfLastThreeMonths();
        return transactionRepository.findTransactionsFromLastThreeMonths(startDate, custId);
    }
}
