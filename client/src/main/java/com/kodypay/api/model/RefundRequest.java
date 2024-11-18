package com.kodypay.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/** RefundRequest. */
public class RefundRequest {
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("printMerchantReceipt")
    private boolean printMerchantReceipt = false;
    @JsonProperty("printShopperReceipt")
    private boolean printShopperReceipt = false;

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

    public RefundRequest printMerchantReceipt(boolean printMerchantReceipt) {
        this.printMerchantReceipt = printMerchantReceipt;
        return this;
    }

    /**
     * Checks if the merchant receipt should be printed.
     * @return true if the merchant receipt should be printed, false otherwise.
     */
    public boolean isPrintMerchantReceipt() {
        return printMerchantReceipt;
    }

    public void setPrintMerchantReceipt(boolean printMerchantReceipt) {
        this.printMerchantReceipt = printMerchantReceipt;
    }

    /**
     * Checks if the shopper receipt should be printed.
     * @return true if the shopper receipt should be printed, false otherwise.
     */
    public boolean isPrintShopperReceipt() {
        return printShopperReceipt;
    }

    public void setPrintShopperReceipt(boolean printShopperReceipt) {
        this.printShopperReceipt = printShopperReceipt;
    }

    public RefundRequest printShopperReceipt(boolean printShopperReceipt) {
        this.printShopperReceipt = printShopperReceipt;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RefundRequest{");
        sb.append("amount='").append(amount).append('\'');
        sb.append(", printMerchantReceipt=").append(printMerchantReceipt);
        sb.append(", printShopperReceipt=").append(printShopperReceipt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RefundRequest that = (RefundRequest) o;
        return printMerchantReceipt == that.printMerchantReceipt && printShopperReceipt == that.printShopperReceipt && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + (printMerchantReceipt ? 1 : 0);
        result = 31 * result + (printShopperReceipt ? 1 : 0);
        return result;
    }
}
