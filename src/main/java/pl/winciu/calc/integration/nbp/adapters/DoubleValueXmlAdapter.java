package pl.winciu.calc.integration.nbp.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Adam Winciorek
 */
public class DoubleValueXmlAdapter extends XmlAdapter<String, Double> {
    @Override
    public Double unmarshal(String v) throws Exception {
        String parsableDouble = v.replace(',', '.');
        return Double.parseDouble(parsableDouble);
    }

    @Override
    public String marshal(Double v) throws Exception {
        return v.toString().replace('.', ',');
    }
}
