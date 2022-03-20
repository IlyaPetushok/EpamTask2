package by.petushokilya.mobile;

import by.petushokilya.mobile.entity.AbstractTarif;
import by.petushokilya.mobile.exception.XmlException;
import by.petushokilya.mobile.parser.TarifSaxBuilder;

public class main {
    public static void main(String[] args) throws XmlException {
        TarifSaxBuilder saxBuilder=new TarifSaxBuilder();
        saxBuilder.buildTarifsSet("src/main/resources/tarif.xml");
    }
}
