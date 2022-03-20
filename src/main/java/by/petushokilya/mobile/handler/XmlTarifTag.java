package by.petushokilya.mobile.handler;

public enum XmlTarifTag {
    TARIFS("tarifs"),
    TARIF("tarif"),
    ID("id"),
    OPERATORNAME("operator_name"),
    NAMETARIF("name_tarif"),
    PAYROLL("payroll"),
    COSTCONNECTTARIF("cost_connect_tarif"),
    DATACONNECT("data_connect"),
    INTERNET("internet"),
    QUANTITYFREEMG("quantity_free_mg"),
    PRICEFORROAMING("price_for_roaming"),
    PRICEADDMG("price_add_mg"),
    CONNECTMETHODS("connect_methods"),
    PRICEINSIDENET("price_inside_net"),
    PRICEOUTSIDENET("price_outside_net"),
    PRICEFORSMS("price_for_sms"),
    PRICECALLSTATSION("price_call_statsion")
    ;

    String value;

    XmlTarifTag(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
