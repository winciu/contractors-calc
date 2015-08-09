package pl.winciu.calc.api.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class EconomicFactorRepresentation {
    private final int taxRate;
    private final Money fixedCosts;

    @JsonCreator
    public EconomicFactorRepresentation(@JsonProperty("taxRate") int taxRate,
                                        @JsonProperty("fixedCostsCurrencyCode") String fixedCostsCurrencyCode,
                                        @JsonProperty("fixedCostsValue") double fixedCostValue) {
        this(taxRate, Money.of(CurrencyUnit.of(fixedCostsCurrencyCode), fixedCostValue));
    }

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
