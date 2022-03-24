package by.petushokilya.mobile.parser;

import by.petushokilya.mobile.builder.AbstractTarifBuilder;
import by.petushokilya.mobile.exception.XmlException;
import by.petushokilya.mobile.handler.TarifHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class TarifSaxBuilder extends AbstractTarifBuilder {
    private static final Logger logger = LogManager.getLogger(TarifSaxBuilder.class.getName());
    private TarifHandler handler = new TarifHandler();
    private XMLReader xmlReader;

    public TarifSaxBuilder() throws XmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException exception) {
            logger.error("TarifSaxBuilder is not create");
            throw new XmlException(exception);
        }

    }

    public void buildTarifsSet(String filename) throws XmlException {
        try {
            xmlReader.parse(filename);
        } catch (IOException | SAXException exception) {
            logger.error("Mistake in parse method Sax", exception);
            throw new XmlException("Mistake in parse method Sax", exception);
        }
    }
}
