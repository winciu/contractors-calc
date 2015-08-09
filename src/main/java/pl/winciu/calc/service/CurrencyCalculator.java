package pl.winciu.calc.service;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import pl.winciu.calc.model.ExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Adam Winciorek
 */
public class CurrencyCalculator {

    public Money convert(Money amountIHave, ExchangeRate exchangeRate) {
        final Money amountIHaveInCurrencyUnits = amountIHave.dividedBy(exchangeRate.getUnits(), RoundingMode.HALF_EVEN);
        final CurrencyUnit targetCurrency = CurrencyUnit.of(exchangeRate.getSourceCurrency());
        return amountIHaveInCurrencyUnits.convertedTo(targetCurrency, BigDecimal.valueOf(exchangeRate.getValue()),
                                                      RoundingMode.HALF_EVEN);
    }
}
