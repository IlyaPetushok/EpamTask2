package by.petushokilya.mobile.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Internet extends AbstractTarif{
    private int minSpeedInternet;
    private int maxSpeedInternet;
    private int quantityFreeMgInMonth;
    private double priceFor1000Mg;
    private double priceForMaxSpeedOnHour;


    public Internet(String id, String nameTarif, Operator operator, double payroll, double costConnectForTarif, LocalDateTime dataConnect, int minSpeedInternet, int maxSpeedInternet, int quantityFreeMgInMonth, double priceFor1000Mg, double priceForMaxSpeedOnHour) {
        super(id, nameTarif, operator, payroll, costConnectForTarif, dataConnect);
        this.minSpeedInternet = minSpeedInternet;
        this.maxSpeedInternet = maxSpeedInternet;
        this.quantityFreeMgInMonth = quantityFreeMgInMonth;
        this.priceFor1000Mg = priceFor1000Mg;
        this.priceForMaxSpeedOnHour = priceForMaxSpeedOnHour;
    }

    public int getMinSpeedInternet() {
        return minSpeedInternet;
    }

    public void setMinSpeedInternet(int minSpeedInternet) {
        this.minSpeedInternet = minSpeedInternet;
    }

    public int getMaxSpeedInternet() {
        return maxSpeedInternet;
    }

    public void setMaxSpeedInternet(int maxSpeedInternet) {
        this.maxSpeedInternet = maxSpeedInternet;
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

    public double getPriceForMaxSpeedOnHour() {
        return priceForMaxSpeedOnHour;
    }

    public void setPriceForMaxSpeedOnHour(double priceForMaxSpeedOnHour) {
        this.priceForMaxSpeedOnHour = priceForMaxSpeedOnHour;
    }

    @Override
    public String toString() {
        return "Internet{" +
                "minSpeedInternet=" + minSpeedInternet +
                ", maxSpeedInternet=" + maxSpeedInternet +
                ", quantityFreeMgInMonth=" + quantityFreeMgInMonth +
                ", priceFor1000Mg=" + priceFor1000Mg +
                ", priceForMaxSpeedOnHour=" + priceForMaxSpeedOnHour +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minSpeedInternet, maxSpeedInternet, quantityFreeMgInMonth, priceFor1000Mg, priceForMaxSpeedOnHour);
    }
}
