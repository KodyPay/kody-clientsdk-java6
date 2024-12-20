package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/** RefundRequest. */
public class RefundRequest {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("idempotencyKey")
    private UUID idempotencyKey = null;

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

    /**
     * Retrieves the idempotency key.
     * @return the idempotency key as a UUID
     */
    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public RefundRequest idempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RefundRequest{");
        sb.append("    amount='").append(amount).append('\'');
        sb.append("    idempotencyKey='").append(idempotencyKey).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RefundRequest that = (RefundRequest) o;
        return amount.equals(that.amount) &&
                Objects.equals(idempotencyKey, that.idempotencyKey);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + amount.hashCode();
        result = 31 * result + (idempotencyKey == null ? 0 : idempotencyKey.hashCode());
        return result;
    }
}
