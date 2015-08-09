package pl.winciu.calc.model;

import javax.persistence.*;
import java.util.Currency;
import java.util.Objects;

/**
 * @author Adam Winciorek
 */
@Entity
@Table(name = "exchange_rates")
@IdClass(ExchangeRateId.class)
public class ExchangeRate {

    @Id
    @Column(name = "provider", nullable = false)
    private String providerName;
    @Id
    @Column(name = "source_currency", nullable = false)
    private Currency sourceCurrency;
    @Id
    @Column(name = "target_currency", nullable = false)
    private Currency targetCurrency;

    @Column(name = "units", nullable = false)
    private int units = 1;

    @Column(name = "value", nullable = false)
    private double value;

    public ExchangeRate(ExchangeRateId id, int units, double value) {
        this.providerName = id.getProviderName();
        this.sourceCurrency = id.getSourceCurrency();
        this.targetCurrency = id.getTargetCurrency();
        this.units = units;
        this.value = value;
    }

    public ExchangeRate() {
        // Default constructor for entity
    }

    public String getProviderName() {
        return providerName;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public int getUnits() {
        return units;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%s %s%s %s (%d)", providerName, sourceCurrency, targetCurrency, value, units);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(getUnits(), that.getUnits()) &&
                Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getProviderName(), that.getProviderName()) &&
                Objects.equals(getSourceCurrency(), that.getSourceCurrency()) &&
                Objects.equals(getTargetCurrency(), that.getTargetCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderName(), getSourceCurrency(), getTargetCurrency(), getUnits(), getValue());
    }
}
