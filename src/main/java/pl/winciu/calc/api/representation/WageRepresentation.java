package pl.winciu.calc.api.representation;

import org.joda.money.Money;

/**
 * @author Adam Winciorek
 */
public class WageRepresentation {
    private final Money netIncome;
    private final Money grossWage;
    private final Money netWage;
    private final WageMetadataRepresentation metadata;

    public WageRepresentation(Money netIncome, Money grossWage, Money netWage,
                              WageMetadataRepresentation metadata) {
        this.netIncome = netIncome;
        this.grossWage = grossWage;
        this.netWage = netWage;
        this.metadata = metadata;
    }

    public Money getNetIncome() {
        return netIncome;
    }

    public Money getGrossWage() {
        return grossWage;
    }

    public Money getNetWage() {
        return netWage;
    }

    public WageMetadataRepresentation getMetadata() {
        return metadata;
    }
}
