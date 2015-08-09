package pl.winciu.calc.integration.nbp.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

/**
 * @author Adam Winciorek
 */
public class BigDecimalValueXmlAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String v) throws Exception {
        String parsableNumber = v.replace(',', '.');
        return new BigDecimal(parsableNumber);
    }

    @Override
    public String marshal(BigDecimal v) throws Exception {
        return v.toString().replace('.', ',');
    }
}
