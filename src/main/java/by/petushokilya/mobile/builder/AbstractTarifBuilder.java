package by.petushokilya.mobile.builder;

import by.petushokilya.mobile.entity.AbstractTarif;
import by.petushokilya.mobile.exception.XmlException;

import java.util.HashSet;
import java.util.Set;

public class AbstractTarifBuilder {
    protected Set<AbstractTarif> tarifs;

    public AbstractTarifBuilder(){
        tarifs = new HashSet<>();
    }

    public AbstractTarifBuilder(Set<AbstractTarif> tarifs){
        this.tarifs = tarifs;
    }

    public Set<AbstractTarif> gettarifs(){
        return tarifs;
    }

    public void buildTarifsSet(String filename) throws XmlException {}
}
