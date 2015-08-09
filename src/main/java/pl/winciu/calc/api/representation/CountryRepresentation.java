package pl.winciu.calc.api.representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.money.CurrencyUnit;

import java.util.Locale;

/**
 * @author Adam Winciorek
 */
public class CountryRepresentation {
    private final String code;
    private final CurrencyUnit currency;
    private final EconomicFactorRepresentation economicFactors;

    @JsonCreator
    public CountryRepresentation(@JsonProperty("code") String code,
                                 @JsonProperty("currencyCode") String currencyCode,
                                 @JsonProperty("economicFactors") EconomicFactorRepresentation economicFactors) {
        this.code = code;
        this.currency = CurrencyUnit.of(currencyCode);
        this.economicFactors = economicFactors;
    }

    public String getCode() {
        return code;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public EconomicFactorRepresentation getEconomicFactors() {
        return economicFactors;
    }

    /**
     * TODO: this should be replaced by resource bundles
     * @return
     */
    public String getCountryName() {
        Locale locale = new Locale("", code);
        return locale.getDisplayCountry(new Locale("pl", "PL"));
    }
}
