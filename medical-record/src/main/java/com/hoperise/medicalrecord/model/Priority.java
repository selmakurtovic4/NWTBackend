package com.hoperise.medicalrecord.model;

public enum Priority {
    NON_URGENT("Non urgent"),
    URGENT("Urgent"),
    VERY_URGENT("Very urgent");
    private String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
