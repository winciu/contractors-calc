package pl.winciu.calc.service;

import org.joda.money.Money;

import java.math.RoundingMode;

/**
 * @author Adam Winciorek
 */
public class Wage {
    private final Money grossValue;
    private final WageMetadata metadata;

    public Wage(Money grossValue,  WageMetadata metadata) {
        this.grossValue = grossValue;
        this.metadata = metadata;
    }

    /**
     * @return post-tax value of a given gross value
     */
    public Money calculateNetValue() {
        return grossValue.minus(calculateTaxAmount());
    }

    /**
     * @return post-tax value substracted with all other fixed spending
     */
    public Money calculateNetIncome() {
        return calculateNetValue().minus(metadata.getFixedCosts());
    }

    /**
     * @return amount evaluated as a given tax rate percentage of a gross value
     */
    public Money calculateTaxAmount() {
        return grossValue.multipliedBy((double)metadata.getTaxRate() / 100d, RoundingMode.HALF_EVEN);
    }

    public Money getGrossValue() {
        return grossValue;
    }

    public WageMetadata getMetadata() {
        return metadata;
    }
}
