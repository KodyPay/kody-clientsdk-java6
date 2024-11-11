package com.kodypay.api;

import com.kodypay.api.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

/**
 * API tests for KodyPayTerminalServiceApi
 */
@Ignore("Manual tests only, this triggers the real API")
public class KodyPayTerminalServiceIT {
    private final String storeId = "7e078533-d85d-451b-ba38-f7672d3b063b";
    private final String terminalId = "S1F2-000158213604086";

    private KodyPayTerminalService api;

    @Before
    public void setUp() {
        api = new KodyPayTerminalService(
                "https://api-development.kodypay.com",
                "SRtC3DIKYlxbG3rlMX8PYUriFkCSFxKESf6Y4q5pIKR9");
    }

    @Test
    public void terminalsTest() throws ApiException {
        TerminalsResponse terminalsResponse = api.terminals(storeId);

        assertNotNull(terminalsResponse);
        List<Terminal> terminalsList = terminalsResponse.getTerminals();
        assertNotNull(terminalsList);
        assertFalse(terminalsList.isEmpty());
        for (int i = 0; i < terminalsList.size(); i++) {
            Terminal terminal = terminalsList.get(i);
            System.out.println(terminal.getTerminalId());
        }
        assertEquals(terminalId, terminalsList.get(0).getTerminalId());
    }

    @Test
    public void payTest() throws ApiException, InterruptedException {
        BigDecimal amount = new BigDecimal("1");
        String amountStr = String.format("%.2f", amount.setScale(2, RoundingMode.HALF_UP));
        assertEquals("1.00", amountStr);

        PayRequest pay = new PayRequest()
                .amount(amountStr)
                .showTips(false);
        PayResponse payResponse = api.pay(storeId, terminalId, pay);

        assertNotNull(payResponse);
        System.out.println(payResponse);
        String orderId = payResponse.getOrderId();

        if (payResponse.getStatus() == PaymentStatus.PENDING) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting for payment to complete..." + orderId);
                Thread.sleep(2000);
                PayResponse detailsResponse = api.paymentDetails(storeId, orderId);

                assertNotNull(detailsResponse);
                System.out.println(detailsResponse);
                if (detailsResponse.getStatus() != PaymentStatus.PENDING) {
                    break;
                }
            }
        }
        System.out.println("Getting final payment details..." + orderId);
        Thread.sleep(5000);
        PayResponse detailsResponse = api.paymentDetails(storeId, orderId);

        assertNotNull(detailsResponse);
        System.out.println(detailsResponse);
    }

    @Test
    public void cancelTest() throws ApiException, InterruptedException {
        PayRequest pay = new PayRequest()
                .amount("1.11")
                .showTips(false);
        PayResponse payResponse = api.pay(storeId, terminalId, pay);

        assertNotNull(payResponse);
        System.out.println(payResponse);
        String orderId = payResponse.getOrderId();

        if (payResponse.getStatus() == PaymentStatus.PENDING) {
            System.out.println("Waiting to cancel payment..." + orderId);
            Thread.sleep(2000);
            CancelRequest cancel = new CancelRequest()
                    .amount("1.11")
                    .orderId(orderId);
            CancelResponse cancelResponse = api.cancel(storeId, terminalId, cancel);

            assertNotNull(cancelResponse);
            System.out.println(cancelResponse);

            System.out.println("Waiting for payment to cancel..." + orderId);
            Thread.sleep(2000);
            PayResponse detailsResponse = api.paymentDetails(storeId, orderId);

            assertNotNull(detailsResponse);
            System.out.println(detailsResponse);
        }
        System.out.println("Getting final payment details..." + orderId);
        Thread.sleep(5000);
        PayResponse detailsResponse = api.paymentDetails(storeId, orderId);

        assertNotNull(detailsResponse);
        System.out.println(detailsResponse);
    }

}
