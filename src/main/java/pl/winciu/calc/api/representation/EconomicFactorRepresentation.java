package pl.winciu.calc.api.representation;

/**
 * @author Adam Winciorek
 */
public class EconomicFactorRepresentation {
    private final double taxRate;
    private final double fixedCosts;

    public EconomicFactorRepresentation(double taxRate, double fixedCosts) {
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getFixedCosts() {
        return fixedCosts;
    }
}
