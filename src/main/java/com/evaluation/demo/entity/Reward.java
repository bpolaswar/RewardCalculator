package com.evaluation.demo.entity;

import java.util.HashMap;
import java.util.Map;

public class Reward {
    Map<String, Double> monthlyPoint = new HashMap<>();
    Double totalPoint = 0.0;

    public Map<String, Double> getMonthlyPoint() {
        return monthlyPoint;
    }

    public void setMonthlyPoint(Map<String, Double> monthlyPoint) {
        this.monthlyPoint = monthlyPoint;
    }

    public Double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public void addPoints(String month, double point) {
        monthlyPoint.put(month, monthlyPoint.getOrDefault(month, 0.0) + point);
        totalPoint += point;
    }
}
