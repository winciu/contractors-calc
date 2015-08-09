package pl.winciu.calc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;

/**
 * @author Adam Winciorek
 */
public class ExchangeRateId implements Serializable {
    private String providerName;
    private Currency sourceCurrency;
    private Currency targetCurrency;

    public ExchangeRateId() {
        // Default constructor for entity
    }

    public ExchangeRateId(String providerName, Currency sourceCurrency, Currency targetCurrency) {
        this.providerName = providerName;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExchangeRateId that = (ExchangeRateId) o;
        return Objects.equals(getProviderName(), that.getProviderName()) &&
                Objects.equals(getSourceCurrency(), that.getSourceCurrency()) &&
                Objects.equals(getTargetCurrency(), that.getTargetCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProviderName(), getSourceCurrency(), getTargetCurrency());
    }
}
