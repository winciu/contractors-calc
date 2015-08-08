package pl.winciu.calc.api.representation;

import java.util.Currency;

/**
 * @author Adam Winciorek
 */
public class CurrencyRepresentation {
    private final Currency code;

    public CurrencyRepresentation(Currency code) {
        this.code = code;
    }

    public Currency getCode() {
        return code;
    }
}
