package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets RefundStatus
 */
public enum RefundStatus {
    PENDING("PENDING"),
    REQUESTED("REQUESTED"),
    FAILED("FAILED"),
    REFUNDED("REFUNDED");

    private final String value;

    RefundStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static RefundStatus fromValue(String value) {
        for (RefundStatus b : RefundStatus.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        return null;
    }
}

