package com.ntneik15.selflearning.retailerapp.dto.order;

public enum StatusOrder {
    Disputed,
    Shipped,
    Cancelled,
    Resolved,
    InProcess,
    OnHold;

    public static StatusOrder getEnum(String value) {
        for (StatusOrder status : values()) {
            if (status.name().equalsIgnoreCase(value.replaceAll("\\s+", ""))) {
                return status;
            }
        }
        return null;
    }
}
