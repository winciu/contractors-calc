package pl.winciu.calc.integration.nbp;

import pl.winciu.calc.integration.nbp.adapters.BigDecimalValueXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

/**
 * @author Adam Winciorek
 */
public class ExchangeRatesTableItem {
    @XmlElement(name = "nazwa_waluty", required = true)
    private String currencyName;

    @XmlElement(name = "przelicznik", required = true)
    private int unit;

    @XmlElement(name = "kod_waluty", required = true)
    private String currencyCode;

    @XmlElement(name = "kurs_kupna")
    @XmlJavaTypeAdapter(BigDecimalValueXmlAdapter.class)
    private BigDecimal buyRate;

    @XmlElement(name = "kurs_sprzedazy")
    @XmlJavaTypeAdapter(BigDecimalValueXmlAdapter.class)
    private BigDecimal sellRate;

    @XmlElement(name = "kurs_sredni")
    @XmlJavaTypeAdapter(BigDecimalValueXmlAdapter.class)
    private BigDecimal averageRate;

    public String getCurrencyName() {
        return currencyName;
    }

    public int getUnit() {
        return unit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }
}
