# Kody Payments API

This guide provides an overview of using the Kody Payments API and its reference documentation.

- [Client Libraries](#client-libraries)
- [Java 6 Installation](#java-6-installation)
- [Authenticate to Payments API](#authenticate-to-payments-api)
- [Payments API Reference](#payments-api-reference)
- [API data reference and Demo code](#api-data-reference-and-demo-code)
- [More sample code](#more-sample-code)

## Client Libraries

Kody provides client libraries for many popular languages to access the APIs. If your desired programming language is supported by the client libraries, we recommend that you use this option.

Available languages:
- Python: https://github.com/KodyPay/kody-clientsdk-python/
- Java : https://github.com/KodyPay/kody-clientsdk-java/ and Java 6: https://github.com/KodyPay/kody-clientsdk-java6/
- PHP: https://github.com/KodyPay/kody-clientsdk-php/
- .Net: https://github.com/KodyPay/kody-clientsdk-dotnet/

The advantages of using the Kody Client library instead of a REST API are:
- Maintained by Kody.
- Built-in authentication and increased security.
- Built-in retries.
- Idiomatic for each language.
- Quicker development.
- Backwards compatibility with new versions.

If your coding language is not listed, please let the Kody team know and we will be able to create it for you.

## Java 6 Installation
### Requirements
- Java6 client supports JDK 6 (to JDK 11)
  - _Note: Please use [Java client](https://github.com/KodyPay/kody-clientsdk-java/) if you are using JDK 17 (or newer)_
- Maven (optional), recommended way to install the SDK

Install the Kody Java6 Client SDK using the following Maven snippet:

```xml
<dependency>
    <groupId>com.kodypay.api</groupId>
    <artifactId>kody-clientsdk-java6</artifactId>
    <version>0.0.1</version>
</dependency>
```
The library can also be downloaded from [here](https://central.sonatype.com/artifact/com.kodypay.api/kody-clientsdk-java6/overview).

### Import in code

````java
import com.kodypay.api.KodyPayTerminalService;
import com.kodypay.api.model.*;
````

## Authenticate to Payments API

The client library uses a combination of a `Store ID` and an `API key`.
These will be shared with you during the technical integration onboarding or by your Kody contact.

During development, you will have access to a **test Store** and **test API key**, and when the integration is ready for live access, the production credentials will be shared securely with you and associated with a live store that was onboarded on Kody.

The test and live API calls are always compatible, only changing credentials and the service hostname is required to enable the integration in production.

### Host names

- Development and test: `https://api-staging.kodypay.com`
- Live: `https://api.kodypay.com`

### API Authentication

Every client library request authenticates with the server using a `storeId` and an `API Key`. The `storeId` is used as a request parameter and the `API Key` is configured when initialising the client.

Example:
````java
String storeId = "UUID of assigned store"; // STORE_ID
KodyPayTerminalService api = new KodyPayTerminalService(HOSTNAME, API_KEY);
TerminalsResponse response = api.terminals(storeId);
````

Replace the `STORE_ID`, `API_KEY` and `HOSTNAME` with the details provided by the Kody team.
Note: it is recommended that you store the `API_KEY` in a secured storage, and insert it into your code via an environment variable.

## Payments API Reference

Kody supports the following channels to accept payments via API.

1. [**Terminal**](#terminal---in-person-payments) - In-person payments

Each of these channels have their own collection of services that are available as method calls on the client library:
- `KodyPayTerminalServiceApi`

### Terminal - In-person payments

The Kody Payments API Terminal service has the following methods:

- [Get List of Terminals](#Get-list-of-Terminals): `KodyPayTerminalService.terminals` - returns all the terminals of the store and their online status
- [Create Terminal Payment](#create-terminal-payment):`KodyPayTerminalService.pay` - initiate a terminal payment
- [Cancel terminal payment](#cancel-terminal-payment): `KodyPayTerminalService.cancel` - cancel an active terminal payment
- [Get Payment Details](#get-terminal-payment-details) `KodyPayTerminalService.paymentDetails` - get the payment details

Follow the links of these methods to see the sample code and the data specification.

## API data reference and Demo code

Every request to the client library requires authentication and the identifier of the store. See more [authentication](#authenticate-to-payments-api).

### Get list of Terminals

This is a simple and read only method, that returns a list of all terminals assigned to the store, and their online status.
You can use this request frequently and it is a good way to check if your API code is configured properly for authentication.

The terminals request requires the following parameters:
- `storeId` - the ID of your assigned store

````java
String storeId = "UUID of assigned store";

TerminalsResponse response = api.terminals(storeId);
````

#### TerminalsResponse : Terminal Response
```java
public class Terminal {
    private String terminalId;  // Terminal serial number
    private boolean online;     // Online status
}

public class TerminalsResponse {
    private List<Terminal> terminals;  // List of Terminal objects
}
```

#### Java6 Demo
````java
//import com.kodypay.api.KodyPayTerminalService;
//import com.kodypay.api.model.*;

String storeId = "UUID of assigned store"; // STORE_ID
KodyPayTerminalService api = new KodyPayTerminalService(HOSTNAME, API_KEY);
TerminalsResponse response = api.terminals(storeId);
````

### Create terminal payment

Send a payment initiation request to a terminal.
This request will either make the terminal immediately display the card acquiring screen, or display a tip screen to the user after which it will go to the card acquiring screen.

A test terminal might have multiple apps on the OS screen. Launch the terminal app called `[S] Payments`.

The terminal must be in the mode: `Wait for Orders` which can be launched from the terminal app menu.
A store that has the feature `Wait for Orders` enabled will always launch the `Wait for Orders` screen automatically.
This screen can be closed (by tapping the `X` icon) to access other terminal features, but payments from API will not work until the `Wait for Orders` screen is started.

#### PayRequest - Payment Request
```java
public class PayRequest {
    private String amount = null;
    private Boolean showTips = null;
}

String storeId = "UUID of assigned store";
String terminalId = "Terminal serial number";
PayResponse response = api.pay(storeId, terminalId, pay);
```

Request parameters:
- `storeId` - the UUID of your assigned store
- `terminalId` - the serial number of the terminal that will process the payment request. This number is returned by the [list of terminals request](#get-list-of-terminals), or can be found on the back label of the hardware.
- `amount` - amount as a 2.dp decimal number, such as `"1.00"`
- `showTips` - (optional) whether to show (true) or hide (false) the tip options. Default is (false)


#### PayResponse : Payment Response

````java
public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED")
}

public class PayResponse {
    private PaymentStatus status = null;            // Status of payment
    private String failureReason = null;            // Optional, reason for failure
    private Map<String, Object> receiptJson = null; // Optional, json blob for receipt data
    private String orderId = null;                  // Unique order ID generated by Kody
    private OffsetDateTime dateCreated = null;      // Timestamp when the response was created
    private String extPaymentRef = null;            // Optional, external payment reference
    private OffsetDateTime datePaid = null;         // Optional, timestamp for date paid
    private String totalAmount = null;              // Optional, total amount
    private String saleAmount = null;               // Optional, sale amount
    private String tipsAmount = null;               // Optional, tips amount
}
````

#### Java6 Demo
````java
//import com.kodypay.api.KodyPayTerminalService;
//import com.kodypay.api.model.*;

KodyPayTerminalService api = new KodyPayTerminalService(HOSTNAME, API_KEY);

String storeId = "UUID of assigned store"; // STORE_ID
String terminalId = "Terminal serial number";
String amount = "1.00";
boolean showTips = false;

PayRequest pay = new PayRequest()
        .amount(amount)
        .showTips(showTips);
PayResponse response = api.pay(storeId, terminalId, pay);

// Note: the response will be returned with a PENDING payment status
// the payment details should be retrieved by the client until the payment either completes (SUCCESS/FAILED) or is CANCELLED (see below).
````

### Get Terminal Payment Details

The payment details request requires the following parameters:
- `storeId` - the ID of your assigned store
- `orderId` - the Order ID returned in the initial payment response, a unique UUID value for each payment.

````java
String storeId = "UUID of assigned store";
String orderId = "UUID of order generated by Kody";

PayResponse response = api.paymentDetails(storeId, orderId);
````
- PayResponse : [Get Payment Detail Response](#payresponse--payment-response)

#### Java6 Demo
````java
//import com.kodypay.api.KodyPayTerminalService;
//import com.kodypay.api.model.*;

KodyPayTerminalService api = new KodyPayTerminalService(HOSTNAME, API_KEY);

String storeId = "UUID of assigned store"; // STORE_ID
String orderId = "UUID of order generated by Kody";

PayResponse response = api.paymentDetails(storeId, orderId);
````

### Cancel Terminal Payment

#### CancelRequest - Cancel Payment Request
```java
public class CancelRequest {
    private String amount = null;
    private String orderId = null;
}

String storeId = "UUID of assigned store";
String terminalId = "Terminal serial number";
CancelResponse response = api.cancel(storeId, terminalId, cancel);
```

The cancel payment request requires the following parameters:

- `storeId` - the ID of your assigned store
- `terminalId` - the serial number of the terminal that is processing the payment request
- `amount` - the amount sent in the original payment request, used to find the payment request
- `orderId` - the Order ID returned in the initial payment response, a unique UUID value for each payment

#### CancelResponse : Cancel Payment Response
````java
public enum PaymentStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED")
}

public class CancelResponse {
    private PaymentStatus status;   //Cancellation status
}
````

#### Java6 Demo
````java
//import com.kodypay.api.KodyPayTerminalService;
//import com.kodypay.api.model.*;

KodyPayTerminalService api = new KodyPayTerminalService(HOSTNAME, API_KEY);

String storeId = "UUID of assigned store"; //STORE_ID
String terminalId = "Terminal serial number";
String amount = "1.00";
String orderId = "UUID of order generated by Kody";

CancelRequest cancel = new CancelRequest()
    .amount(amount)
    .orderId(orderId);
CancelResponse response = api.cancel(storeId, terminalId, cancel);
````

## More sample code

- Java6 : https://github.com/KodyPay/kody-clientsdk-java6/tree/main/samples/src/main/java/terminal
- Java : https://github.com/KodyPay/kody-clientsdk-java/tree/main/samples/src/main/java/terminal
- Python: https://github.com/KodyPay/kody-clientsdk-python/tree/main/versions/3_12/samples/terminal
- PHP: https://github.com/KodyPay/kody-clientsdk-php/tree/main/samples/php8/pos
- .Net: https://github.com/KodyPay/kody-clientsdk-dotnet/tree/main/samples/ListTerminals,https://github.com/KodyPay/kody-clientsdk-dotnet/tree/main/samples/TerminalPayment 
