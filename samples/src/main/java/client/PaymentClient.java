package client;

import com.kodypay.api.KodyPayTerminalService;
import com.kodypay.api.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentClient {
    private static final long TIMEOUT_MS = 1000L * 60 * 3; // timeout after 3 minutes
    private static final Logger LOG = Logger.getLogger(PaymentClient.class.getName());

    private final String baseAddress;
    private final UUID payStoreId;
    private final KodyPayTerminalService api;

    public PaymentClient(URI address, UUID payStoreId, String apiKey) {
        this.baseAddress = address.toString();
        this.payStoreId = payStoreId;
        api = new KodyPayTerminalService(baseAddress, apiKey);
    }

    public PayResponse sendPayment(String terminalId, BigDecimal amount, boolean showTips, PaymentMethodType paymentMethodType, PaymentConsumer consumer) throws ApiException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("sendPayment: storeId=" + payStoreId + ", amount=" + amount + ", terminalId=" + terminalId + " (showTips=" + showTips + ", paymentMethodType=" + paymentMethodType + ")" + " (address: " + baseAddress + ")");
        }

        UUID idempotencyKey = UUID.randomUUID();
        PayRequest pay = new PayRequest()
                .amount(scaleTwo(amount))
                .showTips(showTips)
                .orderId(idempotencyKey)
                .paymentMethod(new PaymentMethod()
                        .paymentMethodType(paymentMethodType)
                );
        PayResponse response = api.pay(payStoreId.toString(), terminalId, pay);
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("sendPayment: response=" + response);
        }

        String orderId = response.getOrderId();
        if (consumer != null) {
            consumer.pending(orderId);
        }

        long start = System.currentTimeMillis();
        do {
            long now = System.currentTimeMillis();
            if (now > start + TIMEOUT_MS) {
                if (LOG.isLoggable(Level.FINE)) {
                    LOG.fine("sendPayment: finished waiting..." + ((now - start) / 1000L) + "s");
                }
                break;
            }
            if (LOG.isLoggable(Level.FINE)) {
                LOG.fine("sendPayment: waiting for response..." + ((now - start) / 1000L) + "s");
            }
            try {
                //noinspection BusyWait
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                return response;
            }
            response = getDetails(orderId);
        } while (response.getStatus() == PaymentStatus.PENDING);

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("sendPayment: finished, response=" + response);
        }
        if (consumer != null) {
            consumer.completed(response);
        }
        return response;
    }

    public PayResponse getDetails(String orderId) throws ApiException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("getDetails: storeId=" + payStoreId + ", orderId=" + orderId + " (address: " + baseAddress + ")");
        }

        PayResponse response = api.paymentDetails(payStoreId.toString(), orderId);

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("getDetails: response=" + response);
        }
        return response;
    }

    public CancelResponse cancelPayment(String terminalId, String orderId, BigDecimal amount) throws ApiException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("cancelPayment: storeId=" + payStoreId + ", amount=" + amount + ", terminalId=" + terminalId + ", orderId=" + orderId + " (address: " + baseAddress + ")");
        }

        CancelRequest cancel = new CancelRequest()
                .amount(scaleTwo(amount))
                .orderId(orderId);
        CancelResponse response = api.cancel(payStoreId.toString(), terminalId, cancel);

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("cancelPayment: response=" + response);
        }
        return response;
    }

    public TerminalsResponse getTerminals() throws ApiException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("getTerminals: storeId=" + payStoreId + " (address: " + baseAddress + ")");
        }

        TerminalsResponse response = api.terminals(payStoreId.toString());

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("getTerminals: response=" + response);
        }
        return response;
    }

    public RefundResponse refundPayment(String orderId, BigDecimal amount) throws ApiException {
        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("refundPayment: storeId=" + payStoreId + ", orderId=" + orderId + ", amount=" + amount + " (address: " + baseAddress + ")");
        }

        UUID idempotencyKey = UUID.randomUUID();
        RefundRequest refund = new RefundRequest()
                .amount(scaleTwo(amount))
                .idempotencyKey(idempotencyKey);
        RefundResponse response = api.refund(payStoreId.toString(), orderId, refund);

        if (LOG.isLoggable(Level.FINE)) {
            LOG.fine("refundPayment: response=" + response);
        }
        return response;
    }

    private String scaleTwo(BigDecimal amount) {
        if (amount == null) {
            return "0.00";
        } else {
            return String.format("%.2f", amount.setScale(2, RoundingMode.HALF_UP));
        }
    }
}
