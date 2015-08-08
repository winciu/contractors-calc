package pl.winciu.calc.integration;

import java.util.*;

/**
 * @author Adam Winciorek
 */
public class ExchangeRates implements Iterable<ExchangeRates.ExchangeRate> {

    private static final ExchangeRates EMPTY_EXCHANGE_RATES = new ExchangeRates();
    private final Set<ExchangeRate> rates;

    public ExchangeRates() {
        rates = new HashSet<>();
    }

    @Override public Iterator<ExchangeRate> iterator() {
        return rates.iterator();
    }

    public void addRate(Currency code, double value) {
        rates.add(new ExchangeRate(value, Currency.getInstance("PLN"), code));
    }

    public static ExchangeRates empty() {
        return EMPTY_EXCHANGE_RATES;
    }

    public boolean isEmpty() {
        return rates.isEmpty();
    }

    class ExchangeRate {
        private final double value;
        private final Currency sourceCode;
        private final Currency targetCode;

        ExchangeRate(double value, Currency sourceCode, Currency targetCode) {
            this.value = value;
            this.sourceCode = sourceCode;
            this.targetCode = targetCode;
        }

        public double getValue() {
            return value;
        }

        public Currency getSourceCode() {
            return sourceCode;
        }

        public Currency getTargetCode() {
            return targetCode;
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
