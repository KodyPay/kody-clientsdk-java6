package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * PaymentSettings
 */
public class PaymentSettings {
  @JsonProperty("showTips")
  private Boolean showTips;

  @JsonProperty("acceptsOnly")
  private List<PaymentSettingsPaymentMethodType> acceptsOnly;

  @JsonProperty("token")
  private String token;

  @JsonProperty("activateQrCodeScanner")
  private Boolean activateQrCodeScanner;

  public PaymentSettings showTips(Boolean showTips) {
    this.showTips = showTips;
    return this;
  }

  public Boolean getShowTips() {
    return showTips;
  }

  public void setShowTips(Boolean showTips) {
    this.showTips = showTips;
  }

  public PaymentSettings acceptsOnly(List<PaymentSettingsPaymentMethodType> acceptsOnly) {
    this.acceptsOnly = acceptsOnly;
    return this;
  }

  public List<PaymentSettingsPaymentMethodType> getAcceptsOnly() {
    return acceptsOnly;
  }

  public void setAcceptsOnly(List<PaymentSettingsPaymentMethodType> acceptsOnly) {
    this.acceptsOnly = acceptsOnly;
  }

  public PaymentSettings token(String token) {
    this.token = token;
    this.activateQrCodeScanner = null;  // oneof field handling
    return this;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
    this.activateQrCodeScanner = null;  // oneof field handling
  }

  public PaymentSettings activateQrCodeScanner(Boolean activateQrCodeScanner) {
    this.activateQrCodeScanner = activateQrCodeScanner;
    this.token = null;  // oneof field handling
    return this;
  }

  public Boolean getActivateQrCodeScanner() {
    return activateQrCodeScanner;
  }

  public void setActivateQrCodeScanner(Boolean activateQrCodeScanner) {
    this.activateQrCodeScanner = activateQrCodeScanner;
    this.token = null;  // oneof field handling
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentSettings that = (PaymentSettings) o;
    return Objects.equals(showTips, that.showTips) &&
            Objects.equals(acceptsOnly, that.acceptsOnly) &&
            Objects.equals(token, that.token) &&
            Objects.equals(activateQrCodeScanner, that.activateQrCodeScanner);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (this.showTips == null ? 0 : this.showTips.hashCode());
    result = 31 * result + (this.acceptsOnly == null ? 0 : this.acceptsOnly.hashCode());
    result = 31 * result + (this.token == null ? 0 : this.token.hashCode());
    result = 31 * result + (this.activateQrCodeScanner == null ? 0 : this.activateQrCodeScanner.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentSettings {\n");
    sb.append("    showTips: ").append(toIndentedString(showTips)).append("\n");
    sb.append("    acceptsOnly: ").append(toIndentedString(acceptsOnly)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    activateQrCodeScanner: ").append(toIndentedString(activateQrCodeScanner)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
