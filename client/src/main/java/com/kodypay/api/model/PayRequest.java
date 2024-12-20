package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
  private UUID orderId = null;

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
   * Get order ID (idempotency key)
   * @return orderId
   **/
  public UUID getOrderId() {
    return orderId;
  }

  public void setOrderId(UUID orderId) {
    this.orderId = orderId;
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

  public PayRequest orderId(UUID orderId) {
    this.orderId = orderId;
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

