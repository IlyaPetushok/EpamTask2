package by.petushokilya.mobile;

import by.petushokilya.mobile.entity.AbstractTarif;
import by.petushokilya.mobile.exception.XmlException;
import by.petushokilya.mobile.parser.TarifDomBuilder;
import by.petushokilya.mobile.parser.TarifSaxBuilder;
import by.petushokilya.mobile.parser.TarifStAXBuilder;

public class main {
    public static void main(String[] args) throws XmlException {
        TarifStAXBuilder saxBuilder=new TarifStAXBuilder();
        saxBuilder.buildTarifsSet("src/main/resources/tarif.xml");
    }
}
