package com.cristianogregio.wex.transactionstore.controller;

import com.cristianogregio.wex.transactionstore.dto.PurchaseTransactionConversionDto;
import com.cristianogregio.wex.transactionstore.model.PurchaseTransaction;
import com.cristianogregio.wex.transactionstore.repository.PurchaseTransactionRepository;
import com.cristianogregio.wex.transactionstore.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class PurchaseTransactionConvertController
{

    private final PurchaseTransactionRepository transactionRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public PurchaseTransactionConvertController(PurchaseTransactionRepository transactionRepository,
                                                CurrencyConversionService currencyConversionService)
    {
        this.transactionRepository = transactionRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/{id}/convert")
    public ResponseEntity<?> convertTransaction(@PathVariable UUID id, @RequestParam String targetCurrency)
    {

        Optional<PurchaseTransaction> transactionOpt = transactionRepository.findById(id);

        if (transactionOpt.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found.");
        }

        PurchaseTransaction transaction = transactionOpt.get();

        // Call for service to convert
        Optional<BigDecimal> convertedAmountOpt = currencyConversionService.convertToTargetCurrency(
                targetCurrency,
                transaction.getAmountUSD(),
                transaction.getTransactionDate().toLocalDate()
        );

        if (convertedAmountOpt.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Currency conversion rate not available within the last 6 months for the specified currency.");
        }

        BigDecimal convertedAmount = convertedAmountOpt.get();
        BigDecimal exchangeRate = convertedAmount.divide(transaction.getAmountUSD(), 2, BigDecimal.ROUND_HALF_UP);

        // Create a meaningful response
        PurchaseTransactionConversionDto response = new PurchaseTransactionConversionDto(
                transaction.getTransactionId(),
                transaction.getDescription(),
                transaction.getTransactionDate(),
                transaction.getAmountUSD(),
                exchangeRate,
                convertedAmount
        );

        return ResponseEntity.ok(response);
    }
}
