package by.petushokilya.mobile.entity;

public enum Roaming {
    ON("Can use roaming"), OFF("Can't use roaming");

    private String status;

    Roaming(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Roaming getStatusRoaming(String name) {
        for (Roaming roaming : Roaming.values()) {
            if (name.equals(roaming.getStatus())) {
                return roaming;
            }
        }
        return null;
    }
}
