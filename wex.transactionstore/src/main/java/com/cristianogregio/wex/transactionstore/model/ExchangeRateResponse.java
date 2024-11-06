package com.cristianogregio.wex.transactionstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {

    @JsonProperty("data")
    private List<ExchangeRateData> data;

    @JsonProperty("meta")
    private Meta meta;

    public List<ExchangeRateData> getData() {
        return data;
    }

    public void setData(List<ExchangeRateData> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExchangeRateData {
        @JsonProperty("country_currency_desc")
        private String countryCurrencyDesc;

        @JsonProperty("exchange_rate")
        private String exchangeRate;

        @JsonProperty("record_date")
        private String recordDate;

        public String getCountryCurrencyDesc() {
            return countryCurrencyDesc;
        }

        public void setCountryCurrencyDesc(String countryCurrencyDesc) {
            this.countryCurrencyDesc = countryCurrencyDesc;
        }

        public String getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(String exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public String getRecordDate() {
            return recordDate;
        }

        public void setRecordDate(String recordDate) {
            this.recordDate = recordDate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        @JsonProperty("count")
        private int count;

        @JsonProperty("total-count")
        private int totalCount;

        @JsonProperty("total-pages")
        private int totalPages;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }
}
