package com.yelnar.repo;

import com.yelnar.entity.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<ExchangeHistory,Long> {

}
