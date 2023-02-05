package com.autoserviceapp.model.enums;

public enum OrderStatus {
    ACCEPTED("Accepted"),
    IN_PROGRESS("In_progress"),
    COMPLETED_SUCCESSFULLY("Completed_successfully"),
    COMPLETED_UNSUCCESSFULLY("Completed_unsuccessfully"),
    PAID("Paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
