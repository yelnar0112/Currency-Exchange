package com.yelnar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_history")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_currency")
    @NonNull
    private String sourceCurrency;

    @Column(name = "target_currency")
    @NonNull
    private String targetCurrency;

    @Column(name = "conversion_multiple")
    @NonNull
    private BigDecimal conversionRate;

    @Column(name = "quantity")
    @NonNull
    private BigDecimal quantity;

    @Column(name = "total_calculated_amount")
    @NonNull
    private BigDecimal totalCalculatedAmount;

    @Column(name = "exchange_time")
    @NonNull
    private LocalDateTime exchangeTime;

    @PrePersist
    private void init() {
        exchangeTime = LocalDateTime.now();
    }
}
