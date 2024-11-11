package client;

import com.kodypay.api.model.PayResponse;

public interface PaymentConsumer {
    void pending(String orderId);
    void completed(PayResponse response);
}
