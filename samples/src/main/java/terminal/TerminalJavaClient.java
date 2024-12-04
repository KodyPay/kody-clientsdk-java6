package terminal;

import client.PaymentClient;
import client.PaymentConsumer;
import com.kodypay.api.model.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Logger;

import static terminal.Utils.loadProperties;

public class TerminalJavaClient {
    private static final Logger LOG = Logger.getLogger(TerminalJavaClient.class.getName());

    private final PaymentClient client;
    private String exTerminalId = null;

    public TerminalJavaClient(String configFile) {
        Properties properties = loadProperties(configFile);
        URI address = URI.create(properties.getProperty("address", "http://localhost"));
        String apiKey = properties.getProperty("apiKey");
        if (apiKey == null)
            throw new IllegalArgumentException("Invalid config, expected apiKey");

        UUID storeId = UUID.fromString(properties.getProperty("storeId"));

        client = new PaymentClient(address, storeId, apiKey);
    }

    public PayResponse sendPayment(final String amountStr, boolean showTips, PaymentMethodType paymentMethodType) throws ApiException {
        LOG.info("sending payment for amount: " + amountStr + " (showTips=" + showTips + ", paymentMethodType=" + paymentMethodType + ") to terminal: " + exTerminalId);
        if (exTerminalId == null) {
            throw new ApiException(400, "Cannot send payment to invalid terminal");
        }

        PaymentConsumer consumer = new PaymentConsumer() {
            private boolean pending = false;

            @Override
            public void pending(final String orderId) {
                LOG.info("Payment received: " + orderId);
                pending = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // optionally cancel payment after 30 seconds
                            for (int i = 0; i < 30; i++) {
                                Thread.sleep(1000L);
                                if (!pending) {
                                    return;
                                }
                            }
                            CancelResponse cancelled = cancelPayment(orderId, amountStr);
                            LOG.fine("Payment cancelled: " + cancelled);
                        } catch (InterruptedException e) {
                            LOG.fine(e.getMessage());
                        } catch (ApiException e) {
                            LOG.info(e.getMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void completed(PayResponse response) {
                LOG.info("Payment completed: " + response);
                pending = false;
            }
        };

        BigDecimal amount = new BigDecimal(amountStr);
        return client.sendPayment(exTerminalId, amount, showTips, paymentMethodType, consumer);
    }

    public PayResponse getDetails(String orderId) throws ApiException {
        LOG.info("Get payment details for: " + orderId);

        PayResponse response = client.getDetails(orderId);
        LOG.info("Payment details: " + response);
        return response;
    }

    public CancelResponse cancelPayment(String orderId, String amountStr) throws ApiException {
        LOG.info("Cancelling payment for order: " + orderId + " with amount: " + amountStr + " at terminal: " + exTerminalId);
        BigDecimal amount = new BigDecimal(amountStr);
        CancelResponse response = client.cancelPayment(exTerminalId, orderId, amount);
        LOG.info("Payment cancelled: " + response);
        return response;
    }

    public TerminalsResponse getTerminals(String defaultTerminalId) throws ApiException {
        LOG.info("Get terminals, default: " + defaultTerminalId);
        TerminalsResponse response = client.getTerminals();

        for (Terminal terminal : response.getTerminals()) {
            if (!terminal.isOnline()) {
                continue;
            }
            if (defaultTerminalId == null || terminal.getTerminalId().equals(defaultTerminalId)) {
                exTerminalId = terminal.getTerminalId();
                break;
            }
        }
        if (exTerminalId == null && !response.getTerminals().isEmpty()) {
            exTerminalId = response.getTerminals().get(0).getTerminalId();
        } else if (exTerminalId == null) {
            LOG.info("Didn't find a terminal");
            return response;
        }

        LOG.info("Selected terminal: " + exTerminalId);
        return response;
    }

    public RefundResponse refundPayment(String orderId, String amountStr) throws ApiException {
        LOG.info("Refunding payment for order: " + orderId + " with amount: " + amountStr);
        BigDecimal amount = new BigDecimal(amountStr);
        RefundResponse response = client.refundPayment(orderId, amount);
        LOG.info("Payment refund requested: " + response);
        return response;
    }
}
