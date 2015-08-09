package pl.winciu.calc;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import pl.winciu.calc.service.Wage;
import pl.winciu.calc.service.WageMetadata;

/**
 * @author Adam Winciorek
 */
public class WageTest {

    private static final CurrencyUnit UNIT = CurrencyUnit.of("PLN");

    @Test
    public void shouldCalculateCorrectNetValue() {
        Wage wage = new Wage(Money.of(UNIT, 750), new WageMetadata(19, Money.of(UNIT, 1200)));
        Assert.assertEquals(Money.of(UNIT, 607.50), wage.calculateNetValue());
    }

    @Test
    public void shouldCalculateCorrectNetIncome() {
        Wage wage = new Wage(Money.of(UNIT, 750), new WageMetadata(19, Money.of(UNIT, 1200)));
        Assert.assertEquals(Money.of(UNIT, -592.50), wage.calculateNetIncome());
    }

    @Test
    public void shouldCalculateCorrectTaxAmount() {
        Wage wage = new Wage(Money.of(UNIT, 750), new WageMetadata(19, Money.of(UNIT, 1200)));
        Assert.assertEquals(Money.of(UNIT, 142.5), wage.calculateTaxAmount());
    }
}
