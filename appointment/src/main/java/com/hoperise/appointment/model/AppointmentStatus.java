package com.hoperise.appointment.model;

public enum AppointmentStatus {
    AVAILABLE("Available"),
    BOOKED("Booked"),
    CANCELLED("Cancelled");
    private String value;
    AppointmentStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
