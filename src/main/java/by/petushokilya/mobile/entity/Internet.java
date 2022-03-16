package by.petushokilya.mobile.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Internet extends AbstractTarif {
    private int quantityFreeMgInMonth;
    private double priceForRoaming;
    private double priceFor1000Mg;

    public Internet(String id, String nameTarif, Operator operator, double payroll,
                    double costConnectForTarif, LocalDateTime dataConnect,
                    int quantityFreeMgInMonth, double priceForRoaming,
                    double priceFor1000Mg) {
        super(id, nameTarif, operator, payroll, costConnectForTarif, dataConnect);
        this.quantityFreeMgInMonth = quantityFreeMgInMonth;
        this.priceForRoaming = priceForRoaming;
        this.priceFor1000Mg = priceFor1000Mg;
    }

    public double getPriceForRoaming() {
        return priceForRoaming;
    }

    public void setPriceForRoaming(double priceForRoaming) {
        this.priceForRoaming = priceForRoaming;
    }


    public int getQuantityFreeMgInMonth() {
        return quantityFreeMgInMonth;
    }

    public void setQuantityFreeMgInMonth(int quantityFreeMgInMonth) {
        this.quantityFreeMgInMonth = quantityFreeMgInMonth;
    }

    public double getPriceFor1000Mg() {
        return priceFor1000Mg;
    }

    public void setPriceFor1000Mg(double priceFor1000Mg) {
        this.priceFor1000Mg = priceFor1000Mg;
    }

    @Override
    public String toString() {
        return "Internet{" +
                "quantityFreeMgInMonth=" + quantityFreeMgInMonth +
                ", priceForRoaming=" + priceForRoaming +
                ", priceFor1000Mg=" + priceFor1000Mg +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantityFreeMgInMonth, priceForRoaming, priceFor1000Mg);
    }
}
