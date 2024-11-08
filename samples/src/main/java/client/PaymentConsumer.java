package client;

public interface PaymentConsumer {
    void pending(String orderId);
    void completed(String orderId);
}
