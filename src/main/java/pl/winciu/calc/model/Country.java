package pl.winciu.calc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;


/**
 * @author Adam Winciorek
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    @Id
    private String code;
    private Currency currencyCode;
    @Embedded
    private EconomicFactors economicFactors;

    public Country() {
        // Default constructor for entity
    }

    public Country(String code, Currency currencyCode, EconomicFactors economicFactors) {
        this.code = code;
        this.currencyCode = currencyCode;
        this.economicFactors = economicFactors;
    }

    public String getCode() {
        return code;
    }

    public Currency getCurrencyCode() {
        return currencyCode;
    }

    public EconomicFactors getEconomicFactors() {
        return economicFactors;
    }
}
