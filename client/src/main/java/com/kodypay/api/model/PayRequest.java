package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

/**
 * PayRequest
 */
public class PayRequest {
  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("showTips")
  private Boolean showTips = null;

  @JsonProperty("paymentMethod")
  private PaymentMethod paymentMethod = null;

  @JsonProperty("orderId")
  private String orderId = null;

  @JsonProperty("paymentReference")
  private String paymentReference = null;

  @JsonProperty("idempotencyUuid")
  private UUID idempotencyUuid = null;

  @JsonProperty("acceptsOnly")
  private List<String> acceptsOnly;

   /**
   * Get amount
   * @return amount
  **/
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

   /**
   * Get showTips
   * @return showTips
  **/
  public Boolean isShowTips() {
    return showTips;
  }

  public void setShowTips(Boolean showTips) {
    this.showTips = showTips;
  }

  /**
   * Get paymentMethod
   * @return paymentMethod
   **/
  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  /**
   * Get order ID (can be used as idempotency key)
   * @return orderId
   **/
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
  public void setOrderId(UUID orderId) {
    this.orderId = orderId.toString();
  }

  /**
   * Get payment reference
   * @return paymentReference
   **/
  public String getPaymentReference() {
    return paymentReference;
  }

  public void setPaymentReference(String paymentReference) {
    this.paymentReference = paymentReference;
  }

  /**
   * Get idempotency key (UUID)
   * @return idempotencyUuid
   **/
  public UUID getIdempotencyUuid() {
    return idempotencyUuid;
  }

  public void setIdempotencyUuid(UUID idempotencyUuid) {
    this.idempotencyUuid = idempotencyUuid;
  }

  /**
   * Get acceptsOnly
   * @return acceptsOnly
   */
  public List<String> getAcceptsOnly() {
    return acceptsOnly;
  }

  public void setAcceptsOnly(List<String> acceptsOnly) {
    this.acceptsOnly = acceptsOnly;
  }

  public PayRequest amount(String amount) {
    this.amount = amount;
    return this;
  }

  public PayRequest showTips(Boolean showTips) {
    this.showTips = showTips;
    return this;
  }

  public PayRequest paymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public PayRequest orderId(String orderId) {
    this.orderId = orderId;
    return this;
  }

  public PayRequest orderId(UUID orderId) {
    this.orderId = orderId.toString();
    return this;
  }

  public PayRequest idempotencyUuid(UUID idempotencyUuid) {
    this.idempotencyUuid = idempotencyUuid;
    return this;
  }

  public PayRequest acceptsOnly(List<String> acceptsOnly) {
    this.acceptsOnly = acceptsOnly;
    return this;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayRequest payRequest = (PayRequest) o;
    return Objects.equals(this.amount, payRequest.amount) &&
        Objects.equals(this.showTips, payRequest.showTips) &&
        Objects.equals(this.orderId, payRequest.orderId);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.amount == null ? 0: this.amount.hashCode());
    result = 31 * result + (this.showTips == null ? 0: this.showTips.hashCode());
    result = 31 * result + (this.orderId == null ? 0: this.orderId.hashCode());
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayRequest {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    showTips: ").append(toIndentedString(showTips)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
    sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
    sb.append("    idempotencyUuid: ").append(toIndentedString(idempotencyUuid)).append("\n");
    sb.append("    acceptsOnly: ").append(toIndentedString(acceptsOnly)).append("\n");

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

