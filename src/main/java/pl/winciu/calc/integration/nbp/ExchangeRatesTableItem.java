package pl.winciu.calc.integration.nbp;

import pl.winciu.calc.integration.nbp.adapters.DoubleValueXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

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
    @XmlJavaTypeAdapter(DoubleValueXmlAdapter.class)
    private Double buyRate;

    @XmlElement(name = "kurs_sprzedazy")
    @XmlJavaTypeAdapter(DoubleValueXmlAdapter.class)
    private Double sellRate;

    @XmlElement(name = "kurs_sredni")
    @XmlJavaTypeAdapter(DoubleValueXmlAdapter.class)
    private Double averageRate;

    public String getCurrencyName() {
        return currencyName;
    }

    public int getUnit() {
        return unit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public double getAverageRate() {
        return averageRate;
    }
}
