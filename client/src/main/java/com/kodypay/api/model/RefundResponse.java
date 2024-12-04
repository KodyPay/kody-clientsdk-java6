package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

import java.util.Map;

/** RefundResponse. */
public class RefundResponse {
    @JsonProperty("status")
    private RefundStatus status;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("failureReason")
    private String failureReason;
    @JsonProperty("dateCreated")
    private OffsetDateTime dateCreated;
    @JsonProperty("totalPaidAmount")
    private String totalPaidAmount;
    @JsonProperty("totalAmountRequested")
    private String totalAmountRequested;
    @JsonProperty("totalAmountRefunded")
    private String totalAmountRefunded;
    @JsonProperty("remainingAmount")
    private String remainingAmount;
    @JsonProperty("paymentTransactionId")
    private String paymentTransactionId;

    /**
     * Gets the status of the refund response.
     * 
     * @return the status of the refund response.
     */
    public RefundStatus getStatus() {
        return status;
    }

    public void setStatus(RefundStatus status) {
        this.status = status;
    }

    /**
     * Gets the order ID associated with the refund response.
     * 
     * @return the order ID.
     */
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the reason for failure, if any.
     * 
     * @return the failure reason.
     */
    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }


    /**
     * Gets the date and time when the refund response was created.
     * 
     * @return the date created.
     */
    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Gets the total amount paid.
     * 
     * @return the total paid amount.
     */
    public String getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(String totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    /**
     * Gets the total amount requested for a refund.
     * 
     * @return the total amount requested.
     */
    public String getTotalAmountRequested() {
        return totalAmountRequested;
    }

    public void setTotalAmountRequested(String totalAmountRequested) {
        this.totalAmountRequested = totalAmountRequested;
    }

    /**
     * Gets the total amount that has been refunded.
     * 
     * @return the total amount refunded.
     */
    public String getTotalAmountRefunded() {
        return totalAmountRefunded;
    }

    public void setTotalAmountRefunded(String totalAmountRefunded) {
        this.totalAmountRefunded = totalAmountRefunded;
    }

    /**
     * Gets the remaining amount that is yet to be refunded.
     * 
     * @return the remaining amount.
     */
    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    /**
     * Gets the payment transaction ID associated with the refund response.
     *
     * @return the payment transaction id.
     */

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RefundResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", orderId='").append(orderId).append('\'');
        sb.append(", failureReason='").append(failureReason).append('\'');
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", totalPaidAmount='").append(totalPaidAmount).append('\'');
        sb.append(", totalAmountRequested='").append(totalAmountRequested).append('\'');
        sb.append(", totalAmountRefunded='").append(totalAmountRefunded).append('\'');
        sb.append(", remainingAmount='").append(remainingAmount).append('\'');
        sb.append(", paymentTransactionId='").append(paymentTransactionId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RefundResponse that = (RefundResponse) o;
        return status.equals(that.status)
                && orderId.equals(that.orderId)
                && (failureReason == null ? that.failureReason == null : failureReason.equals(that.failureReason))
                && dateCreated.equals(that.dateCreated)
                && totalPaidAmount.equals(that.totalPaidAmount)
                && totalAmountRequested.equals(that.totalAmountRequested)
                && totalAmountRefunded.equals(that.totalAmountRefunded)
                && remainingAmount.equals(that.remainingAmount)
                && paymentTransactionId.equals(that.paymentTransactionId);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + orderId.hashCode();
        result = 31 * result + (failureReason != null ? failureReason.hashCode() : 0);
        result = 31 * result + dateCreated.hashCode();
        result = 31 * result + totalPaidAmount.hashCode();
        result = 31 * result + totalAmountRequested.hashCode();
        result = 31 * result + totalAmountRefunded.hashCode();
        result = 31 * result + remainingAmount.hashCode();
        result = 31 * result + paymentTransactionId.hashCode();
        return result;
    }
}
