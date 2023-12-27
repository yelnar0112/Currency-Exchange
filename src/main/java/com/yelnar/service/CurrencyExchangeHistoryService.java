package com.yelnar.service;

import com.yelnar.entity.ExchangeHistory;
import com.yelnar.repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CurrencyExchangeHistoryService {

    private final CurrencyRepo historyRepository;

    @Autowired
    public CurrencyExchangeHistoryService(CurrencyRepo historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveExchangeHistory(String sourceCurrency, String targetCurrency,
                                    BigDecimal conversionRate, BigDecimal quantity,
                                    BigDecimal totalCalculatedAmount) {
        ExchangeHistory exchangeHistory = new ExchangeHistory();
        exchangeHistory.setSourceCurrency(sourceCurrency);
        exchangeHistory.setTargetCurrency(targetCurrency);
        exchangeHistory.setConversionRate(conversionRate);
        exchangeHistory.setQuantity(quantity);
        exchangeHistory.setTotalCalculatedAmount(totalCalculatedAmount);
        exchangeHistory.setExchangeTime(LocalDateTime.now());

        historyRepository.save(exchangeHistory);
    }


}

