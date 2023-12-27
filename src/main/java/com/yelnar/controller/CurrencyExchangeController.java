package com.yelnar.controller;

import com.yelnar.entity.ExchangeHistory;
import com.yelnar.service.CurrencyExchangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class CurrencyExchangeController {

    private final Map<String, BigDecimal> conversionRates = new HashMap<>();
    private final CurrencyExchangeHistoryService historyService;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeHistoryService historyService) {
        this.historyService = historyService;
        conversionRates.put("KZT_TO_EUR", new BigDecimal("500.635"));
        conversionRates.put("KZT_TO_USD", new BigDecimal("450.0058"));
    }

    @GetMapping("/currency/exchange/{from}/to/{to}/quantity/{quantity}")
    public ExchangeHistory convertCurrency(@PathVariable String from,
                                           @PathVariable String to,
                                           @PathVariable BigDecimal quantity) {

        String conversionKey = from + "_TO_" + to;

        if (!conversionRates.containsKey(conversionKey)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversion rate not available");
        }

        BigDecimal conversionRate = conversionRates.get(conversionKey);
        BigDecimal totalCalculatedAmount = quantity.multiply(conversionRate);

        // Save exchange history
        historyService.saveExchangeHistory(from, to, conversionRate, quantity, totalCalculatedAmount);

        return new ExchangeHistory(from, to, conversionRate, quantity, totalCalculatedAmount, LocalDateTime.now());
    }
}

