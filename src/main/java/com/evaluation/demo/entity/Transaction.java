package com.evaluation.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Transaction entity to tract transaction details of customer
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String custId;
    @Column
    private Date txDate;
    @Column
    private double txAmount;

    public Transaction(String custId, Date txDate, double txAmount) {
        this.custId = custId;
        this.txDate = txDate;
        this.txAmount = txAmount;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public double getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(double txAmount) {
        this.txAmount = txAmount;
    }

    public void setTxAmount(Long txAmount) {
        this.txAmount = txAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "custId=" + custId +
                ", txDate=" + txDate +
                ", txAmount=" + txAmount +
                '}';
    }
}
