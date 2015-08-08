package pl.winciu.calc.model;

import javax.persistence.*;
import java.util.Currency;

/**
 * @author Adam Winciorek
 */
@Entity
@Table(name = "exchange_rates", uniqueConstraints = {@UniqueConstraint(name = "currency_pair_u", columnNames =
        {"source_currency", "target_currency"})})
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "source_currency", nullable = false)
    private Currency sourceCurrency;
    @Column(name = "target_currency", nullable = false)
    private Currency targetCurrency;
    @Column(name = "value", nullable = false)
    private double value;

    public ExchangeRate(Currency sourceCurrency, Currency targetCurrency, double value) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.value = value;
    }
}
