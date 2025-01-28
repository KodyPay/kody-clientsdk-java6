package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets public enum PaymentMethodType
 */
public enum PaymentMethodType {
  CARD("CARD"),
  ALIPAY("ALIPAY"),
  WECHAT("WECHAT");

  private final String value;

  PaymentMethodType(String value) {
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
  public static PaymentMethodType fromValue(String value) {
    for (PaymentMethodType b : PaymentMethodType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    return null;
  }
}

