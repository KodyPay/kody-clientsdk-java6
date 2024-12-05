package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PayRequest
 */
public class PaymentMethod {
  @JsonProperty("paymentMethodType")
  private PaymentMethodType paymentMethodType = null;

  /**
   * Get paymentMethodType
   * @return paymentMethodType
   **/
  public PaymentMethodType getPaymentMethodType() {
    return paymentMethodType;
  }

  public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
    this.paymentMethodType = paymentMethodType;
  }

  public PaymentMethod paymentMethodType(PaymentMethodType paymentMethodType) {
    this.paymentMethodType = paymentMethodType;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentMethod paymentMethod = (PaymentMethod) o;
    return Objects.equals(this.paymentMethodType, paymentMethod.paymentMethodType);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.paymentMethodType == null ? 0: this.paymentMethodType.hashCode());
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethod {\n");
    
    sb.append("    paymentMethodType: ").append(toIndentedString(paymentMethodType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

