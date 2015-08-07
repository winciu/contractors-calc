package pl.winciu.calc.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;


/**
 * @author Adam Winciorek
 */
@Entity
@Table(name = "countries")
public class Country implements Serializable{

    @Id
    private String code;
    private Currency currency;
    @Embedded
    private EconomicFactors economicFactors;

    public Country() {
    }

    public Country(String code, Currency currency, EconomicFactors economicFactors) {
        this.code = code;
        this.currency = currency;
        this.economicFactors = economicFactors;
    }

    public Currency getCurrency() {
        return currency;
    }

}
