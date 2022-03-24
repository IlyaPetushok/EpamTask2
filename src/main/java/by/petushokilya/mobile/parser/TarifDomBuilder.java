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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//исправить

public class TarifDomBuilder extends AbstractTarifBuilder {
    private static final Logger logger = LogManager.getLogger(TarifDomBuilder.class.getName());
    private DocumentBuilder documentBuilder;
    private Set<AbstractTarif> tarifs;

    public TarifDomBuilder() throws XmlException {
        tarifs = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException exception) {
            logger.error("Error parse configuration", exception);
            throw new XmlException("Error parse configuration", exception);
        }
    }

    public void buildTarifsSet(String filename) {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList tarifList = root.getElementsByTagName("tarif");
            for (int i = 0; i < tarifList.getLength(); i++) {
                Element tarifElement = (Element) tarifList.item(i);
                AbstractTarif abstractTarif = buildTarif(tarifElement);
                tarifs.add(abstractTarif);
            }
        } catch (IOException | SAXException exception) {
            logger.error("Error in Dom Parser", exception);
        }
    }

    private AbstractTarif buildTarif(Element element) {
        AbstractTarif abstractTarif = new Internet();
        Internet internet = new Internet();
        ConnectMethods connectMethods = new ConnectMethods();

        abstractTarif.setId(element.getAttribute(XmlTarifTag.ID.getValue()));
        abstractTarif.setOperator(Operator.getNameOperator(element.getAttribute((XmlTarifTag.OPERATORNAME.getValue()))));
        abstractTarif.setNameTarif(getElementTextContent(element, XmlTarifTag.NAMETARIF.getValue()));
        abstractTarif.setPayroll(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PAYROLL.getValue())));
        abstractTarif.setCostConnectForTarif((Double.parseDouble(getElementTextContent(element, XmlTarifTag.COSTCONNECTTARIF.getValue()))));
        abstractTarif.setDataConnect(LocalDateTime.parse(getElementTextContent(element, XmlTarifTag.DATACONNECT.getValue())));
        internet.setQuantityFreeMgInMonth(Integer.parseInt(getElementTextContent(element, XmlTarifTag.QUANTITYFREEMG.getValue())));
        internet.setPriceFor1000Mg(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICEADDMG.getValue())));
        internet.setPriceForRoaming(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICEFORROAMING.getValue())));
        connectMethods.setPriceInsideNet(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICEINSIDENET.getValue())));
        connectMethods.setPriceOutsideNet(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICEOUTSIDENET.getValue())));
        connectMethods.setPriceForSms(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICEFORSMS.getValue())));
        connectMethods.setPriceForCallStatsionar(Double.parseDouble(getElementTextContent(element, XmlTarifTag.PRICECALLSTATSION.getValue())));

        return abstractTarif;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
