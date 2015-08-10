package pl.winciu.calc.api.representation;

import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class WageMetadataRepresentation {
    private final Money tax;
    private final int taxRate;
    private final Money fixedCosts;

    public WageMetadataRepresentation(Money tax, int taxRate, Money fixedCosts) {
        this.tax = tax;
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }

    public Money getTax() {
        return tax;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public Money getFixedCosts() {
        return fixedCosts;
    }
}
