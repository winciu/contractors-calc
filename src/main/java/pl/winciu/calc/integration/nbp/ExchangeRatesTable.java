package pl.winciu.calc.integration.nbp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Adam Winciorek
 */
@XmlRootElement(name = "tabela_kursow")
public class ExchangeRatesTable implements Iterable<ExchangeRatesTableItem> {
    @XmlElement(name = "numer_tabeli")
    private String tableNumber;

    @XmlElement(name = "data_notowania", required = false)
    private Date tradingDate;

    @XmlElement(name = "data_publikacji", required = true)
    private Date publicationDate;

    @XmlElement(name = "pozycja")
    private List<ExchangeRatesTableItem> items;

    public String getTableNumber() {
        return tableNumber;
    }

    public Date getTradingDate() {
        return tradingDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    @Override public Iterator<ExchangeRatesTableItem> iterator() {
        return items.iterator();
    }
}
