package pl.winciu.calc.api.representation;

import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class WageMetadataRepresentation {
    private final Money taxAmount;
    private final int taxRate;
    private final Money fixedCosts;

    public WageMetadataRepresentation(Money taxAmount, int taxRate, Money fixedCosts) {
        this.taxAmount = taxAmount;
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }

    public Money getTaxAmount() {
        return taxAmount;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public Money getFixedCosts() {
        return fixedCosts;
    }
}
