package pl.winciu.calc.service;

import java.math.BigDecimal;

/**
 * @author Adam Winciorek
 */
public class NetGrossCalculator {

    public BigDecimal asGross(BigDecimal netValue, double percent) {
        final BigDecimal taxAmount = netValue.multiply(percentAsFraction(percent));
        return netValue.add(taxAmount);
    }

    private BigDecimal percentAsFraction(double percent) {
        return BigDecimal.valueOf(percent).movePointLeft(2);
    }
}
