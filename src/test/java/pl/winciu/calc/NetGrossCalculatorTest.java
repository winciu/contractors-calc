package pl.winciu.calc;

import org.junit.Assert;
import org.junit.Test;
import pl.winciu.calc.service.NetGrossCalculator;

import java.math.BigDecimal;

/**
 * @author Adam Winciorek
 */
public class NetGrossCalculatorTest {
    private static final BigDecimal GROSS_VALUE = new BigDecimal("18450.00");
    private static final BigDecimal NET_VALUE = BigDecimal.valueOf(15000);
    private static final int TAX = 23;

    @Test
    public void shouldCalculateCorrectNetValueFromGrossValue() {
        NetGrossCalculator calculator = new NetGrossCalculator();
        final BigDecimal gross = calculator.asGross(NET_VALUE, TAX);
        Assert.assertEquals(GROSS_VALUE, gross);
    }
}
