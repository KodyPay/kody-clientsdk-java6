package com.kodypay.api;

import com.kodypay.api.model.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * API tests for KodyPayTerminalService
 */
@Ignore("Manual tests only, this triggers the real API")
public class KodyPayTerminalServiceIT {
    public static final String LOGGING_PROPERTIES = "src/test/resources/logging.properties";
    public static final String CONFIG_PROPERTIES = "src/test/resources/dev-config.properties";
    //    public static final String CONFIG_PROPERTIES = "src/test/resources/staging-config.properties";
    private static String address;
    private static String apiKey;
    private static String storeId;
    private static String terminalId;

    private KodyPayTerminalService api;

    @BeforeClass
    public static void setUpClass() throws IOException {
        String loggingPath = new File(LOGGING_PROPERTIES).getAbsolutePath();
        System.out.println("Setting up logging..." + loggingPath);
        System.setProperty("java.util.logging.config.file", loggingPath);

        String configPath = new File(CONFIG_PROPERTIES).getAbsolutePath();
        System.out.println("Setting up config..." + configPath);
        Properties props = new Properties();
        props.load(new FileInputStream(configPath));
        System.out.println("Config: " + props);
        address = props.getProperty("address");
        apiKey = props.getProperty("apiKey");
        storeId = props.getProperty("storeId");
        terminalId = props.getProperty("terminalId");
    }

    @Before
    public void setUp() {
        api = new KodyPayTerminalService(address, apiKey);
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

        PaymentMethod paymentMethod = new PaymentMethod().paymentMethodType(PaymentMethodType.ALIPAY);

        PayRequest pay = new PayRequest()
                .amount(amountStr)
                .showTips(false)
                .paymentMethod(paymentMethod);

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
        Thread.sleep(10000);
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

    @Test
    public void refundTest() throws ApiException, InterruptedException {
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
        Thread.sleep(10000);
        PayResponse detailsResponse = api.paymentDetails(storeId, orderId);

        assertNotNull(detailsResponse);
        System.out.println(detailsResponse);

        if (detailsResponse.getStatus() == PaymentStatus.SUCCESS) {
            System.out.println("Refunding payment...");
            RefundRequest refund = new RefundRequest()
                    .amount("0.50");
            RefundResponse refundResponse = api.refund(storeId, orderId, refund);
            assertNotNull(refundResponse);
            System.out.println(refundResponse);
        } else {
            System.out.println("Cannot refund - invalid status..." + detailsResponse.getStatus());
        }
    }

}
