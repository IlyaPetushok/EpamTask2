package by.petushokilya.mobile.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ConnectMethods extends AbstractTarif {
    private double priceInsideNet;
    private double priceOutsideNet;
    private double priceForCallStatsionar;
    private double priceForSms;

    public ConnectMethods() {
    }

    public ConnectMethods(String id, String nameTarif, Operator operator, double payroll, double costConnectForTarif,
                          LocalDateTime dataConnect, double priceInsideNet, double priceOutsideNet, double priceForCallStatsionar,
                          double priceForSms) {
        super(id, nameTarif, operator, payroll, costConnectForTarif, dataConnect);
        this.priceInsideNet = priceInsideNet;
        this.priceOutsideNet = priceOutsideNet;
        this.priceForCallStatsionar = priceForCallStatsionar;
        this.priceForSms = priceForSms;
    }

    public double getPriceInsideNet() {
        return priceInsideNet;
    }

    public void setPriceInsideNet(double priceInsideNet) {
        this.priceInsideNet = priceInsideNet;
    }

    public double getPriceOutsideNet() {
        return priceOutsideNet;
    }

    public void setPriceOutsideNet(double priceOutsideNet) {
        this.priceOutsideNet = priceOutsideNet;
    }

    public double getPriceForCallStatsionar() {
        return priceForCallStatsionar;
    }

    public void setPriceForCallStatsionar(double priceForCallStatsionar) {
        this.priceForCallStatsionar = priceForCallStatsionar;
    }

    public double getPriceForSms() {
        return priceForSms;
    }

    public void setPriceForSms(double priceForSms) {
        this.priceForSms = priceForSms;
    }

    @Override
    public String toString() {
        return "ConnectMethods{" +
                "priceInsideNet=" + priceInsideNet +
                ", priceOutsideNet=" + priceOutsideNet +
                ", priceForCallStatsionar=" + priceForCallStatsionar +
                ", priceForSms=" + priceForSms +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priceInsideNet, priceOutsideNet, priceForCallStatsionar, priceForSms);
    }
}
