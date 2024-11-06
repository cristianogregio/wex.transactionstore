package com.cristianogregio.wex.transactionstore.service;

import com.cristianogregio.wex.transactionstore.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyConversionService
{

    private final RestTemplate restTemplate;

    @Value("${EXCHANGE_RATE_API_URL}")
    private String EXCHANGE_RATE_API_URL;

    public CurrencyConversionService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public Optional<BigDecimal> convertToTargetCurrency(String targetCurrency, BigDecimal amountUSD, LocalDate transactionDate)
    {

        ExchangeRateResponse response = fetchExchangeRates(targetCurrency, transactionDate);

        if (response == null || response.getData() == null)
        {
            return Optional.empty();
        }

        // This is a filter for rate based on the country current and the interval of dates
        List<ExchangeRateResponse.ExchangeRateData> filteredRates = response.getData().stream()
                .filter(rate -> rate.getCountryCurrencyDesc().equalsIgnoreCase(targetCurrency)
                        && isDateWithinLastSixMonths(rate.getRecordDate(), transactionDate))
                .sorted((r1, r2) -> r2.getRecordDate().compareTo(r1.getRecordDate())) // get the most recent
                .collect(Collectors.toList());

        if (filteredRates.isEmpty())
        {
            return Optional.empty();
        }

        BigDecimal exchangeRate = new BigDecimal(filteredRates.get(0).getExchangeRate());
        BigDecimal convertedAmount = amountUSD.multiply(exchangeRate).setScale(2, BigDecimal.ROUND_HALF_UP);

        return Optional.of(convertedAmount);
    }

    private ExchangeRateResponse fetchExchangeRates(String targetCurrency, LocalDate transactionDate)
    {
        try {
            String filters = "&filter=country_currency_desc:in:" + targetCurrency + ",record_date:" + transactionDate.toString();
            return restTemplate.getForObject(EXCHANGE_RATE_API_URL + filters, ExchangeRateResponse.class);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isDateWithinLastSixMonths(String recordDateStr, LocalDate transactionDate)
    {
        LocalDate recordDate = LocalDate.parse(recordDateStr);
        LocalDate sixMonthsAgo = transactionDate.minusMonths(6);
        return (recordDate.isEqual(transactionDate) || recordDate.isBefore(transactionDate)) && recordDate.isAfter(sixMonthsAgo);
    }
}
