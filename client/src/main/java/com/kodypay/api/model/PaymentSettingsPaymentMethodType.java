package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PaymentMethods
 */
public enum PaymentSettingsPaymentMethodType {
  ALIPAY("ALIPAY"),
  WECHAT("WECHAT"),
  MASTERCARD("MASTERCARD"),
  AMEX("AMEX"),
  BAN_CONTACT("BAN_CONTACT"),
  CHINA_UNION_PAY("CHINA_UNION_PAY"),
  MAESTRO("MAESTRO"),
  DINERS("DINERS"),
  DISCOVER("DISCOVER"),
  JCB("JCB"),
  VISA("VISA");

  private final String value;

  PaymentSettingsPaymentMethodType(String value) {
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
  public static PaymentSettingsPaymentMethodType fromValue(String value) {
    for (PaymentSettingsPaymentMethodType b : PaymentSettingsPaymentMethodType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    return null;
  }
}
