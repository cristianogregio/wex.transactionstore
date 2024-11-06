package com.cristianogregio.wex.transactionstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConversionResponse
{
    private UUID transactionId;
    private String description;
    private LocalDateTime transactionDate;
    private BigDecimal originalAmountUSD;
    private BigDecimal exchangeRate;
    private BigDecimal convertedAmount;

    public ConversionResponse(UUID transactionId, String description, LocalDateTime transactionDate, BigDecimal amountUSD, BigDecimal rate, BigDecimal convertedAmount) {
    }

    public UUID getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId)
    {
        this.transactionId = transactionId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public LocalDateTime getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getOriginalAmountUSD()
    {
        return originalAmountUSD;
    }

    public void setOriginalAmountUSD(BigDecimal originalAmountUSD)
    {
        this.originalAmountUSD = originalAmountUSD;
    }

    public BigDecimal getExchangeRate()
    {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate)
    {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getConvertedAmount()
    {
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount)
    {
        this.convertedAmount = convertedAmount;
    }
}