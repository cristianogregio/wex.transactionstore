package com.cristianogregio.wex.transactionstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PurchaseTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID transactionId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionState state;

    @Size(min = 2, max = 50, message = "Description must have up to 50 characters.")
    public String description;

    @NotNull
    public LocalDateTime transactionDate;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "The amount must be a positive value")
    @Digits(integer = 10, fraction = 2, message = "The amount must have up to 2 decimal places")
    public BigDecimal amountUSD;

    public UUID getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId)
    {
        this.transactionId = transactionId;
    }

    public TransactionState getState()
    {
        return state;
    }

    public void setState(TransactionState state)
    {
        this.state = state;
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

    public BigDecimal getAmountUSD()
    {
        return amountUSD;
    }

    public void setAmountUSD(BigDecimal amountUSD)
    {
        this.amountUSD = amountUSD;
    }

    @Override
    public String toString()
    {
        return "PurchaseTransaction{" +
                "transactionId=" + transactionId +
                ", state=" + state +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", amountUSD=" + amountUSD +
                '}';
    }
}
