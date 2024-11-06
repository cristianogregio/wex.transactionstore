package com.cristianogregio.wex.transactionstore.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseTransactionConversionDto {
    private UUID transactionId;
    private String description;
    private LocalDateTime transactionDate;
    private BigDecimal amountUSD;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;

    public PurchaseTransactionConversionDto(UUID transactionId, String description, LocalDateTime transactionDate,
                                                 BigDecimal amountUSD, BigDecimal exchangeRate, BigDecimal convertedAmount) {
        this.transactionId = transactionId;
        this.description = description;
        this.transactionDate = transactionDate;
        this.amountUSD = amountUSD;
        this.exchangeRate = exchangeRate;
        this.convertedAmount = convertedAmount;
    }

    // Getters e Setters
    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmountUSD() {
        return amountUSD;
    }

    public void setAmountUSD(BigDecimal amountUSD) {
        this.amountUSD = amountUSD;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
