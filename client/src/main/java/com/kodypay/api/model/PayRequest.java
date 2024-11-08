package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PayRequest
 */
public class PayRequest {
  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("showTips")
  private Boolean showTips = null;

  public PayRequest amount(String amount) {
    this.amount = amount;
    return this;
  }

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

  public PayRequest showTips(Boolean showTips) {
    this.showTips = showTips;
    return this;
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
        Objects.equals(this.showTips, payRequest.showTips);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.amount == null ? 0: this.amount.hashCode());
    result = 31 * result + (this.showTips == null ? 0: this.showTips.hashCode());
    return result;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayRequest {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    showTips: ").append(toIndentedString(showTips)).append("\n");
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

