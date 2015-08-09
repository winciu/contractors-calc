package pl.winciu.calc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;


/**
 * @author Adam Winciorek
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    @Id
    private String code;
    private Currency currency;
    @Embedded
    private EconomicFactors economicFactors;

    public Country() {
        // Default constructor for entity
    }

    public Country(String code, Currency currency, EconomicFactors economicFactors) {
        this.code = code;
        this.currency = currency;
        this.economicFactors = economicFactors;
    }

    public String getCode() {
        return code;
    }

    public Currency getCurrency() {
        return currency;
    }

    public EconomicFactors getEconomicFactors() {
        return economicFactors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(getCode(), country.getCode()) &&
                Objects.equals(getCurrency(), country.getCurrency()) &&
                Objects.equals(getEconomicFactors(), country.getEconomicFactors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getCurrency(), getEconomicFactors());
    }
}
