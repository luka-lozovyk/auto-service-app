package com.autoserviceapp.model.enums;

public enum SalaryStatus {
    PAID("Paid"),
    NOT_PAID("Not_paid");

    private String value;

    SalaryStatus(String value) {
        this.value = value;
    }
}
