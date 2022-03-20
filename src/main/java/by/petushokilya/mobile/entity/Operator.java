package by.petushokilya.mobile.entity;

public enum Operator {
    MTC("mtc"), LIFE("life"), A1("A1"), BEELINE("beeline");

    private String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static Operator getNameOperator(String name) {
        for (Operator operator : Operator.values()) {
            if (name.equals(operator.getOperator())) {
                return operator;
            }
        }
        return null;
    }
}
