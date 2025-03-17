package com.evaluation.demo.service;

import com.evaluation.demo.entity.Reward;
import com.evaluation.demo.entity.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RewardServiceTest {

    ObjectMapper objectMapper;
    @Mock
    private TransactionService transactionService;
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        rewardService = new RewardService(transactionService);
        objectMapper = new ObjectMapper();
    }

    @Test
    void calculateRewardPointReturnsLastThreeMonthsPointsSuccessfully() throws IOException {
        List<Transaction> transaction = objectMapper
                .readValue(new File("src/test/java/resources/transaction.json"),
                        new TypeReference<>() {
                        });

        when(transactionService.getTransactionsFromLastThreeMonths(anyString()))
                .thenReturn(transaction);

        Map<String, Reward> map = rewardService.calculateRewardPoint("CUST101");
        assertNotNull(map);
        assertEquals(map.get("CUST101").getTotalPoint(), 3342.0);
        assertEquals(map.get("CUST101").getMonthlyPoint().size(), 3);
    }

    @Test
    void calculateRewardPointReturnsEmptyObjectWhenNoTransactionFound() {
        when(transactionService.getTransactionsFromLastThreeMonths(anyString()))
                .thenReturn(List.of());
        Map<String, Reward> map = rewardService.calculateRewardPoint("CUST101");
        assertNotNull(map);
        assertEquals(map.size(), 0);
    }
}
