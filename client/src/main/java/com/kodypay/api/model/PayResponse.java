package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

import java.util.Map;

/**
 * PayResponse
 */
public class PayResponse {
  @JsonProperty("status")
  private PaymentStatus status = null;

  @JsonProperty("failureReason")
  private String failureReason = null;

  @JsonProperty("receiptJson")
  private Map<String, Object> receiptJson = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("dateCreated")
  private OffsetDateTime dateCreated = null;

  @JsonProperty("extPaymentRef")
  private String extPaymentRef = null;

  @JsonProperty("datePaid")
  private OffsetDateTime datePaid = null;

  @JsonProperty("totalAmount")
  private String totalAmount = null;

  @JsonProperty("saleAmount")
  private String saleAmount = null;

  @JsonProperty("tipsAmount")
  private String tipsAmount = null;

  public PayResponse status(PaymentStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public PayResponse failureReason(String failureReason) {
    this.failureReason = failureReason;
    return this;
  }

   /**
   * Get failureReason
   * @return failureReason
  **/
  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public PayResponse receiptJson(Map<String, Object> receiptJson) {
    this.receiptJson = receiptJson;
    return this;
  }

   /**
   * Get receiptJson
   * @return receiptJson
  **/
  public Map<String, Object> getReceiptJson() {
    return receiptJson;
  }

  public void setReceiptJson(Map<String, Object> receiptJson) {
    this.receiptJson = receiptJson;
  }

  public PayResponse orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

   /**
   * Get orderId
   * @return orderId
  **/
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public PayResponse dateCreated(OffsetDateTime dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

   /**
   * Get dateCreated
   * @return dateCreated
  **/
  public OffsetDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(OffsetDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  public PayResponse extPaymentRef(String extPaymentRef) {
    this.extPaymentRef = extPaymentRef;
    return this;
  }

   /**
   * Get extPaymentRef
   * @return extPaymentRef
  **/
  public String getExtPaymentRef() {
    return extPaymentRef;
  }

  public void setExtPaymentRef(String extPaymentRef) {
    this.extPaymentRef = extPaymentRef;
  }

  public PayResponse datePaid(OffsetDateTime datePaid) {
    this.datePaid = datePaid;
    return this;
  }

   /**
   * Get datePaid
   * @return datePaid
  **/
  public OffsetDateTime getDatePaid() {
    return datePaid;
  }

  public void setDatePaid(OffsetDateTime datePaid) {
    this.datePaid = datePaid;
  }

  public PayResponse totalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

   /**
   * Get totalAmount
   * @return totalAmount
  **/
  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public PayResponse saleAmount(String saleAmount) {
    this.saleAmount = saleAmount;
    return this;
  }

   /**
   * Get saleAmount
   * @return saleAmount
  **/
  public String getSaleAmount() {
    return saleAmount;
  }

  public void setSaleAmount(String saleAmount) {
    this.saleAmount = saleAmount;
  }

  public PayResponse tipsAmount(String tipsAmount) {
    this.tipsAmount = tipsAmount;
    return this;
  }

   /**
   * Get tipsAmount
   * @return tipsAmount
  **/
  public String getTipsAmount() {
    return tipsAmount;
  }

  public void setTipsAmount(String tipsAmount) {
    this.tipsAmount = tipsAmount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayResponse that = (PayResponse) o;
    return Objects.equals(this.status, that.status) &&
            Objects.equals(this.failureReason, that.failureReason) &&
            Objects.equals(this.receiptJson, that.receiptJson) &&
            Objects.equals(this.orderId, that.orderId) &&
            Objects.equals(this.dateCreated, that.dateCreated) &&
            Objects.equals(this.extPaymentRef, that.extPaymentRef) &&
            Objects.equals(this.datePaid, that.datePaid) &&
            Objects.equals(this.totalAmount, that.totalAmount) &&
            Objects.equals(this.saleAmount, that.saleAmount) &&
            Objects.equals(this.tipsAmount, that.tipsAmount);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (failureReason != null ? failureReason.hashCode() : 0);
    result = 31 * result + (receiptJson != null ? receiptJson.hashCode() : 0);
    result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
    result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
    result = 31 * result + (extPaymentRef != null ? extPaymentRef.hashCode() : 0);
    result = 31 * result + (datePaid != null ? datePaid.hashCode() : 0);
    result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
    result = 31 * result + (saleAmount != null ? saleAmount.hashCode() : 0);
    result = 31 * result + (tipsAmount != null ? tipsAmount.hashCode() : 0);
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayResponse {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    failureReason: ").append(toIndentedString(failureReason)).append("\n");
    sb.append("    receiptJson: ").append(toIndentedString(receiptJson)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    dateCreated: ").append(toIndentedString(dateCreated)).append("\n");
    sb.append("    extPaymentRef: ").append(toIndentedString(extPaymentRef)).append("\n");
    sb.append("    datePaid: ").append(toIndentedString(datePaid)).append("\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
    sb.append("    saleAmount: ").append(toIndentedString(saleAmount)).append("\n");
    sb.append("    tipsAmount: ").append(toIndentedString(tipsAmount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

