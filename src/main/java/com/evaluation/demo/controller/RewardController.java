package com.evaluation.demo.controller;

import com.evaluation.demo.Exception.TransactionNotFoundException;
import com.evaluation.demo.entity.Reward;
import com.evaluation.demo.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reward")
public class RewardController {
    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Rest endpoint to calculate last 3-month rewards of customer based on current month
     *
     * @param custId
     * @return map where customerId is key and monthly reward points and total reward points are as a value
     */
    @GetMapping("/calculate-reward-point/{custId}")
    ResponseEntity<Map<String, Reward>> calculateRewardPoint(@PathVariable String custId) {
        Map<String, Reward> response = rewardService.calculateRewardPoint(custId);
        if (response.isEmpty()) {
            throw new TransactionNotFoundException("No transaction found for customer.");
        }
        return ResponseEntity.ok(response);
    }
}
