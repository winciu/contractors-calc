package pl.winciu.calc.api.representation;

/**
 * @author Adam Winciorek
 */
public class CountryRepresentation {
    private final String code;
    private final CurrencyRepresentation currency;
    private final EconomicFactorRepresentation economicFactors;

    public CountryRepresentation(String code, CurrencyRepresentation currency,
                                 EconomicFactorRepresentation economicFactors) {
        this.code = code;
        this.currency = currency;
        this.economicFactors = economicFactors;
    }

    public String getCode() {
        return code;
    }

    public CurrencyRepresentation getCurrency() {
        return currency;
    }

    public EconomicFactorRepresentation getEconomicFactors() {
        return economicFactors;
    }
}
