package by.petushokilya.mobile.parser;

import by.petushokilya.mobile.builder.AbstractTarifBuilder;
import by.petushokilya.mobile.entity.AbstractTarif;
import by.petushokilya.mobile.entity.ConnectMethods;
import by.petushokilya.mobile.entity.Internet;
import by.petushokilya.mobile.entity.Operator;
import by.petushokilya.mobile.exception.XmlException;
import by.petushokilya.mobile.handler.XmlTarifTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static by.petushokilya.mobile.handler.XmlTarifTag.TARIF;

public class TarifStAXBuilder extends AbstractTarifBuilder {
    private static final Logger logger = LogManager.getLogger(TarifStAXBuilder.class.getName());
    private XMLInputFactory xmlInputFactory;
    private Set<AbstractTarif> tarifs;
    private XMLStreamReader reader;


    public TarifStAXBuilder() {
        xmlInputFactory = XMLInputFactory.newInstance();
        tarifs = new HashSet<AbstractTarif>();
    }

    public Set<AbstractTarif> getTarif() {
        return tarifs;
    }

    @Override
    public void buildTarifsSet(String filename) throws XmlException {

        String name;

        try (FileInputStream inputStream = new FileInputStream(filename)) {
            reader = xmlInputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TARIF.getValue())) {
                        AbstractTarif tariff = buildTarif(reader);
                        tarifs.add(tariff);
                    }
                }

            }
        } catch (IOException | XMLStreamException exception) {
            logger.error(exception);
        }
    }

    public AbstractTarif buildTarif(XMLStreamReader xmlStreamReader) throws XMLStreamException, XmlException {
        AbstractTarif currentTariff = new Internet();
        currentTariff.setId(reader.getAttributeValue(null, XmlTarifTag.ID.getValue()));
        currentTariff.setOperator(Operator.getNameOperator(reader.getAttributeValue(null, XmlTarifTag.OPERATORNAME.getValue())));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().replace("_", "").toUpperCase();
                    switch (XmlTarifTag.valueOf(name)) {
                        case NAMETARIF -> currentTariff.setNameTarif(getXMLText(reader));
                        case PAYROLL -> currentTariff.setPayroll(Double.parseDouble(getXMLText(reader)));
                        case COSTCONNECTTARIF -> currentTariff.setCostConnectForTarif(Double.parseDouble(getXMLText(reader)));
                        case DATACONNECT -> currentTariff.setDataConnect(LocalDateTime.parse(getXMLText(reader)));
                        case INTERNET -> buildInternetParametrs(reader);
                        case CONNECTMETHODS -> buildConnectParametrs(reader);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName().replace("_", "");
                    if (XmlTarifTag.valueOf(name.toUpperCase()) == TARIF) {
                        return currentTariff;
                    }
                }
            }
        }
        throw new XmlException("Mistake with StaxParser");
    }

    private Internet buildInternetParametrs(XMLStreamReader reader) throws XMLStreamException, XmlException {
        Internet internet = new Internet();
        XmlTarifTag xmlTarifTag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().replace("_", "").toUpperCase();
                    xmlTarifTag = XmlTarifTag.valueOf(name.toUpperCase());
                    switch (xmlTarifTag) {
                        case QUANTITYFREEMG -> internet.setQuantityFreeMgInMonth(Integer.parseInt(getXMLText(reader)));
                        case PRICEFORROAMING -> internet.setPriceForRoaming(Double.parseDouble(getXMLText(reader)));
                        case PRICEADDMG -> internet.setPriceFor1000Mg(Double.parseDouble(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    xmlTarifTag = XmlTarifTag.valueOf(name.toUpperCase().replace("_", ""));
                    if (xmlTarifTag == XmlTarifTag.INTERNET) {
                        return internet;
                    }
                }
            }
        }
        throw new XmlException("Error build internet parametrs");
    }

    private ConnectMethods buildConnectParametrs(XMLStreamReader reader) throws XMLStreamException, XmlException {
        ConnectMethods connectMethods = new ConnectMethods();
        XmlTarifTag xmlTarifTag;
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName().replace("_", "").toUpperCase();
                    xmlTarifTag = XmlTarifTag.valueOf(name.toUpperCase());
                    switch (xmlTarifTag) {
                        case PRICEINSIDENET -> connectMethods.setPriceInsideNet(Double.parseDouble(getXMLText(reader)));
                        case PRICEOUTSIDENET -> connectMethods.setPriceOutsideNet(Double.parseDouble(getXMLText(reader)));
                        case PRICEFORSMS -> connectMethods.setPriceForSms(Double.parseDouble(getXMLText(reader)));
                        case PRICECALLSTATSION -> connectMethods.setPriceForCallStatsionar(Double.parseDouble(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    xmlTarifTag = XmlTarifTag.valueOf(name.toUpperCase().replace("_", ""));
                    if (xmlTarifTag == XmlTarifTag.CONNECTMETHODS) {
                        return connectMethods;
                    }
                }
            }
        }
        throw new XmlException("Error build internet parametrs");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
