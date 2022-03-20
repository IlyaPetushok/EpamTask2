package by.petushokilya.mobile.handler;

import by.petushokilya.mobile.entity.AbstractTarif;
import by.petushokilya.mobile.entity.ConnectMethods;
import by.petushokilya.mobile.entity.Internet;
import by.petushokilya.mobile.entity.Operator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class TarifHandler extends DefaultHandler {
    private Set<AbstractTarif> tarif;
    private EnumSet<XmlTarifTag> xmlTags;

    private AbstractTarif currentTarif;
    private XmlTarifTag currentXmlTag;
    private Internet internetForTarif;
    private ConnectMethods connectMethods;

    public TarifHandler() {
        tarif = new HashSet<>();
        xmlTags = EnumSet.range(XmlTarifTag.NAMETARIF, XmlTarifTag.PRICECALLSTATSION);
    }

    public Set<AbstractTarif> getTarif() {
        return tarif;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(XmlTarifTag.TARIF.getValue())) {
            String id = attributes.getValue(XmlTarifTag.ID.getValue());
            String operatorName = attributes.getValue(XmlTarifTag.OPERATORNAME.getValue());
            if (qName.equals(XmlTarifTag.INTERNET.getValue())) {
                currentTarif = new Internet();
            } else {
                currentTarif = new ConnectMethods();
            }
            currentTarif.setId(id);
            currentTarif.setOperator(Operator.getNameOperator(operatorName));
        } else {
            //error
            XmlTarifTag temp = XmlTarifTag.valueOf(qName.toUpperCase().replace("_", ""));
            if (xmlTags.contains(temp)) {
                currentXmlTag = temp;
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(XmlTarifTag.INTERNET.getValue()) || qName.equals(XmlTarifTag.INTERNET.getValue())) {
            tarif.add(currentTarif);
            currentTarif = null;
        }

    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);

        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAMETARIF -> currentTarif.setNameTarif(data);
                case PAYROLL -> currentTarif.setPayroll(Double.parseDouble(data));
                case COSTCONNECTTARIF -> currentTarif.setCostConnectForTarif(Double.parseDouble(data));
                case DATACONNECT -> currentTarif.setDataConnect(LocalDateTime.parse(data));
                case QUANTITYFREEMG -> {

                        internetForTarif = (Internet) currentTarif;
                        internetForTarif.setQuantityFreeMgInMonth(Integer.parseInt(data));

                }
                case PRICEFORROAMING -> {
                    if (currentTarif instanceof Internet) {
                        internetForTarif = (Internet) currentTarif;
                        internetForTarif.setPriceForRoaming(Double.parseDouble(data));
                    }
                }
                case PRICEADDMG -> {
                    if (currentTarif instanceof Internet) {
                        internetForTarif = (Internet) currentTarif;
                        internetForTarif.setPriceFor1000Mg(Double.parseDouble(data));
                    }
                }
                case PRICEINSIDENET -> {
                    if (currentTarif instanceof ConnectMethods) {
                        connectMethods = (ConnectMethods) currentTarif;
                        connectMethods.setPriceInsideNet(Double.parseDouble(data));
                    }
                }
                case PRICEOUTSIDENET -> {
                    if (currentTarif instanceof ConnectMethods) {
                        connectMethods = (ConnectMethods) currentTarif;
                        connectMethods.setPriceOutsideNet(Double.parseDouble(data));
                    }
                }
                case PRICEFORSMS -> {
                    if (currentTarif instanceof ConnectMethods) {
                        connectMethods = (ConnectMethods) currentTarif;
                        connectMethods.setPriceForSms(Double.parseDouble(data));
                    }
                }
                case PRICECALLSTATSION -> {
                    if (currentTarif instanceof ConnectMethods) {
                        connectMethods = (ConnectMethods) currentTarif;
                        connectMethods.setPriceForCallStatsionar(Double.parseDouble(data));
                    }
                }
//                default ->
            }
        }
        currentXmlTag = null;
    }

}
