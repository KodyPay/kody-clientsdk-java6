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
    private static final TerminalJavaClient terminalClient = new TerminalJavaClient();
    private static final String CONFIG_FILE = "samples/config.properties";
//    private static final String CONFIG_FILE = "samples/dev-config.properties";

    private final PaymentClient client;
    private String exTerminalId = null;

    public TerminalJavaClient() {
        Properties properties = loadProperties(CONFIG_FILE);
        URI address = URI.create(properties.getProperty("address", "http://localhost"));
        String apiKey = properties.getProperty("apiKey");
        if (apiKey == null)
            throw new IllegalArgumentException("Invalid config, expected apiKey");

        UUID storeId = UUID.fromString(properties.getProperty("storeId"));

        client = new PaymentClient(address, storeId, apiKey);
    }

    public PayResponse sendPayment(final String amountStr) throws ApiException {
        return sendPayment(amountStr, false);
    }

    public PayResponse sendPayment(final String amountStr, boolean showTips) throws ApiException {
        LOG.info("sending payment for amount: " + amountStr + " (showTips=" + showTips + ") to terminal: " + exTerminalId);

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
            public void completed(String orderId) {
                LOG.info("Payment completed: " + orderId);
                pending = false;
            }
        };

        BigDecimal amount = new BigDecimal(amountStr);
        return client.sendPayment(exTerminalId, amount, showTips, consumer);
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
        LOG.info("Get terminals");
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
        if (exTerminalId == null) {
            exTerminalId = response.getTerminals().get(0).getTerminalId();
        }

        LOG.info("Selected terminal: " + exTerminalId);
        return response;
    }

    public static void listTerminals() {
        try {
            String defaultTerminalId = loadProperties(CONFIG_FILE).getProperty("terminalId");
            TerminalsResponse terminals = terminalClient.getTerminals(defaultTerminalId);
            StringBuilder sb = new StringBuilder();
            for (Terminal terminal : terminals.getTerminals()) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(terminal.getTerminalId()).append(" (Online: ").append(terminal.isOnline()).append(")");
            }
            LOG.info("Terminals: " + sb);
        } catch (ApiException e) {
            LOG.severe(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            String amountStr = "1.00";
            boolean isShowTips = false;

            listTerminals();
            PayResponse completed = terminalClient.sendPayment(amountStr, isShowTips);
            LOG.info("Completed payment: " + completed);

            String orderId = completed.getOrderId();
            try {
                LOG.info("Waiting for details: " + orderId);
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                LOG.fine(e.getMessage());
            }
            PayResponse details = terminalClient.getDetails(orderId);
            LOG.info("Payment details: " + details);

            PaymentStatus status = details.getStatus();
            LOG.info("Payment status: " + status);

            if (status == PaymentStatus.PENDING) {
                CancelResponse cancel = terminalClient.cancelPayment(orderId, amountStr);
                LOG.info("Payment is cancelled? " + (cancel.getStatus() == PaymentStatus.CANCELLED));
            }
        } catch (ApiException e) {
            LOG.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
