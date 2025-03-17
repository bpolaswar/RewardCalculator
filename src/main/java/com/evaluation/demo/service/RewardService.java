package com.evaluation.demo.service;

import com.evaluation.demo.Exception.TransactionNotFoundException;
import com.evaluation.demo.entity.Reward;
import com.evaluation.demo.entity.Transaction;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService {

    private final TransactionService transactionService;

    public RewardService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * This method calculate reward for customer
     *
     * @param custId
     * @return map where key is customer id and value is there rewards
     */
    public Map<String, Reward> calculateRewardPoint(String custId) {
        Map<String, Reward> monthlyRewardMap = new HashMap<>();
        List<Transaction> transactionList = transactionService.getTransactionsFromLastThreeMonths(custId);

        for (Transaction t : transactionList) {
            Double amount = 0.0;
            String customerId = t.getCustId();
            String month = new SimpleDateFormat("MMMM")
                    .format(t.getTxDate());
            monthlyRewardMap.putIfAbsent(customerId, new Reward());

            if (t.getTxAmount() > 100) {
                amount += 2 * (t.getTxAmount() - 100);
            }
            if (t.getTxAmount() > 50) {
                amount += 1 * Math.min(t.getTxAmount() - 50, 50);
            }
            monthlyRewardMap.get(customerId).addPoints(month, amount);
        }
        return monthlyRewardMap;
    }
}
