package calc.domain.model;

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
    }

    public EconomicFactors(double taxRate, double fixedCosts) {
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }
}
