package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** RefundRequest. */
public class RefundRequest {
    @JsonProperty("amount")
    private String amount;

    /**
     * Gets the amount for the refund request.
     * @return the amount as a String.
     */
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public RefundRequest amount(String amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RefundRequest{");
        sb.append("amount='").append(amount).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RefundRequest that = (RefundRequest) o;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return amount.hashCode();
    }
}
