package com.evaluation.demo.controller;

import com.evaluation.demo.entity.Reward;
import com.evaluation.demo.service.RewardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @MockitoBean
    private RewardService rewardService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private RewardController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new RewardController(rewardService);
        objectMapper = new ObjectMapper();
    }

    @Test
    void calculateRewardPointReturnsOKStatus() throws Exception {
        Map<String, Reward> map = objectMapper
                .readValue(new File("src/test/java/resources/data.json"), Map.class);
        when(rewardService.calculateRewardPoint(anyString())).thenReturn(map);
        mockMvc.perform(get("/reward/calculate-reward-point/{custId}", "CUST101")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    /**
     * When no transactions found for given customer
     * @throws Exception
     */
    @Test
    public void calculateRewardPointThrowsNotFoundMessageWhenNoTransactionsFound() throws Exception {
        mockMvc.perform(get("/reward/calculate-reward-point/{custId}", "CUST104").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No transaction found for customer."));
    }
}
