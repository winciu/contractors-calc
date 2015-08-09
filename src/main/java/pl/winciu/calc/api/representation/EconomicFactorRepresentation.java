package pl.winciu.calc.api.representation;

import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class EconomicFactorRepresentation {
    private final int taxRate;
    private final Money fixedCosts;

    public EconomicFactorRepresentation(int taxRate, Money fixedCosts) {
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
