package pl.winciu.calc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Adam Winciorek
 */
@Embeddable
public class EconomicFactors implements Serializable {
    @Column(name = "tax_rate", nullable = false)
    private double taxRate;
    @Column(name = "fixed_costs", nullable = false)
    private double fixedCosts;

    public EconomicFactors() {
        // Default constructor for entity
    }

    public EconomicFactors(double taxRate, double fixedCosts) {
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
