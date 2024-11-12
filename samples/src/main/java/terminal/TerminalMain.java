package terminal;

import com.kodypay.api.model.*;

import java.util.logging.Logger;

import static terminal.Utils.loadProperties;

public class TerminalMain {
    // configure logging using -Djava.util.logging.config.file=samples/logging.properties
    private static final Logger LOG = Logger.getLogger(TerminalMain.class.getName());
//    private static final String CONFIG_FILE = "samples/config.properties";
    private static final String CONFIG_FILE = "samples/dev-config.properties";
    private static final TerminalJavaClient terminalClient = new TerminalJavaClient(CONFIG_FILE);

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
