package io.humanscape.opensources.playservices;

public enum GooglePlayServicesStatus {
    AVAILABLE(10),
    GMS_DISABLED(20),
    GMS_NEED_UPDATE(21),
    INVALID(30);

    private int status;
    GooglePlayServicesStatus(int i) {
        this.status = i;
    }
    public int getStatus() {
        return this.status;
    }
}
