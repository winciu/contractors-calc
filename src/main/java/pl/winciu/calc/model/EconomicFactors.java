package pl.winciu.calc.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Adam Winciorek
 */
@Embeddable
public class EconomicFactors implements Serializable {
    @Column(name = "tax_rate", nullable = false)
    private int taxRate;
    @Column(name = "fixed_costs", nullable = false)
    private double fixedCosts;

    public EconomicFactors() {
        // Default constructor for entity
    }

    public EconomicFactors(int taxRate, double fixedCosts) {
        this.taxRate = taxRate;
        this.fixedCosts = fixedCosts;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public double getFixedCosts() {
        return fixedCosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EconomicFactors that = (EconomicFactors) o;
        return Objects.equals(getTaxRate(), that.getTaxRate()) &&
                Objects.equals(getFixedCosts(), that.getFixedCosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaxRate(), getFixedCosts());
    }
}
