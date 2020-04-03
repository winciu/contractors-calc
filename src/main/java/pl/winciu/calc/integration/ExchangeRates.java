package pl.winciu.calc.integration;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Adam Winciorek
 */
public class ExchangeRates implements Iterable<ExchangeRates.ExchangeRate> {

    private static final ExchangeRates EMPTY_EXCHANGE_RATES = new ExchangeRates(null);
    private final String providerName;
    private final Set<ExchangeRate> rates;

    /**
     *
     * @param providerName
     */
    public ExchangeRates(String providerName) {
        this.providerName = providerName;
        rates = new HashSet<>();
    }

    /**
     *
     * @return
     */
    public String getProviderName() {
        return providerName;
    }

    @Override public Iterator<ExchangeRate> iterator() {
        return rates.iterator();
    }

    public void addRate(Currency code, BigDecimal value, int unit) {
        rates.add(new ExchangeRate(value, Currency.getInstance("PLN"), code, unit));
    }

    public static ExchangeRates empty() {
        return EMPTY_EXCHANGE_RATES;
    }

    public boolean isEmpty() {
        return rates.isEmpty();
    }

    class ExchangeRate {
        private final BigDecimal value;
        private final Currency sourceCode;
        private final Currency targetCode;
        private final int units;

        ExchangeRate(BigDecimal value, Currency sourceCode, Currency targetCode, int units) {
            this.value = value;
            this.sourceCode = sourceCode;
            this.targetCode = targetCode;
            this.units = units;
        }

        public BigDecimal getValue() {
            return value;
        }

        public Currency getSourceCode() {
            return sourceCode;
        }

        public Currency getTargetCode() {
            return targetCode;
        }

        public int getUnits() {
            return units;
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
            return Objects.equals(getValue(), that.getValue()) &&
                    Objects.equals(getSourceCode(), that.getSourceCode()) &&
                    Objects.equals(getTargetCode(), that.getTargetCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue(), getSourceCode(), getTargetCode());
        }
    }
}
