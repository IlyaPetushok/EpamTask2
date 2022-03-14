package by.petushokilya.mobile.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractTarif {
    private String id;
    private String nameTarif;
    private Operator operator;
    private double payroll;
    private double costConnectForTarif;
    private LocalDateTime dataConnect;

    public AbstractTarif() {
    }

    public AbstractTarif(String id, String nameTarif, Operator operator, double payroll, double costConnectForTarif, LocalDateTime dataConnect) {
        this.id = id;
        this.nameTarif = nameTarif;
        this.operator = operator;
        this.payroll = payroll;
        this.costConnectForTarif = costConnectForTarif;
        this.dataConnect = dataConnect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTarif() {
        return nameTarif;
    }

    public void setNameTarif(String nameTarif) {
        this.nameTarif = nameTarif;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public double getPayroll() {
        return payroll;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public double getCostConnectForTarif() {
        return costConnectForTarif;
    }

    public void setCostConnectForTarif(double costConnectForTarif) {
        this.costConnectForTarif = costConnectForTarif;
    }

    public LocalDateTime getDataConnect() {
        return dataConnect;
    }

    public void setDataConnect(LocalDateTime dataConnect) {
        this.dataConnect = dataConnect;
    }

    @Override
    public String toString() {
        return "AbstractTarif{" +
                "id='" + id + '\'' +
                ", nameTarif='" + nameTarif + '\'' +
                ", operator=" + operator +
                ", payroll=" + payroll +
                ", costConnectForTarif=" + costConnectForTarif +
                ", dataConnect=" + dataConnect +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, nameTarif, operator, payroll, costConnectForTarif, dataConnect);
    }
}
