package pl.winciu.calc.service;

import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class WageMetadata {
    private final int taxRate;
    private final Money fixedCosts;

    public WageMetadata(int taxRate, Money fixedCosts) {
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public Money getFixedCosts() {
        return fixedCosts;
    }
}
