package com.kodypay.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.kodypay.api.client.ApiClient;
import com.kodypay.api.model.*;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * KodyPayTerminalService provides methods to interact with the KodyPay API for handling terminal-related operations.
 */
public class KodyPayTerminalService {
  private static final Logger LOG = Logger.getLogger(KodyPayTerminalService.class.getName());
  private final ApiClient apiClient;

  public KodyPayTerminalService(String basePath, String apiKey) {
    apiClient = new ApiClient(basePath, apiKey);
  }

  /**
   * Cancel a payment.
   * 
   * @param storeId UUID of store (required)
   * @param terminalId to identify the terminal where the payment was sent (required)
   * @param body cancel request (required)
   * @return CancelResponse
   * @throws ApiException if fails to make API call
   */
  public CancelResponse cancel(String storeId, String terminalId, CancelRequest body) throws ApiException {
    return kodyPayTerminalServiceCancelWithHttpInfo(storeId, terminalId, body).getData();
  }

  ApiResponse<CancelResponse> kodyPayTerminalServiceCancelWithHttpInfo(String storeId, String terminalId, CancelRequest body) throws ApiException {
    LOG.finest("cancel: storeId=" + storeId + ", terminalId=" + terminalId + ", body=" + body);
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      LOG.severe("cancel: storeId is null");
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling cancel request");
    }
    
    // verify the required parameter 'terminalId' is set
    if (terminalId == null) {
      LOG.severe("cancel: terminalId is null");
      throw new ApiException(400, "Missing the required parameter 'terminalId' when calling cancel request");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      LOG.severe("cancel: body is null");
      throw new ApiException(400, "Missing the required parameter 'body' when calling cancel request");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/cancel/{storeId}/{terminalId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId))
      .replaceAll("\\{" + "terminalId" + "\\}", apiClient.escapeString(terminalId));

    final TypeReference<CancelResponse> typeRef = new TypeReference<CancelResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", body, typeRef);
  }

  /**
   * Create a payment.
   * 
   * @param storeId UUID of store (required)
   * @param terminalId send the payment to this terminal serial number (required)
   * @param body payment request (required)
   * @return PayResponse
   * @throws ApiException if fails to make API call
   */
  public PayResponse pay(String storeId, String terminalId, PayRequest body) throws ApiException {
    return kodyPayTerminalServicePayWithHttpInfo(storeId, terminalId, body).getData();
  }

  ApiResponse<PayResponse> kodyPayTerminalServicePayWithHttpInfo(String storeId, String terminalId, PayRequest body) throws ApiException {
    LOG.finest("pay: storeId=" + storeId + ", terminalId=" + terminalId + ", body=" + body);
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      LOG.severe("pay: storeId is null");
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling pay request");
    }
    
    // verify the required parameter 'terminalId' is set
    if (terminalId == null) {
      LOG.severe("pay: terminalId is null");
      throw new ApiException(400, "Missing the required parameter 'terminalId' when calling pay request");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      LOG.severe("pay: body is null");
      throw new ApiException(400, "Missing the required parameter 'body' when calling pay request");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/payment/{storeId}/{terminalId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId))
      .replaceAll("\\{" + "terminalId" + "\\}", apiClient.escapeString(terminalId));

    final TypeReference<PayResponse> typeRef = new TypeReference<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", body, typeRef);
  }

  /**
   * Get payment details.
   * 
   * @param storeId UUID of store (required)
   * @param orderId to identify the payment (order) (required)
   * @return PayResponse
   * @throws ApiException if fails to make API call
   */
  public PayResponse paymentDetails(String storeId, String orderId) throws ApiException {
    return kodyPayTerminalServicePaymentDetailsWithHttpInfo(storeId, orderId).getData();
  }

  ApiResponse<PayResponse> kodyPayTerminalServicePaymentDetailsWithHttpInfo(String storeId, String orderId) throws ApiException {
    LOG.finest("paymentDetails: storeId=" + storeId + ", orderId=" + orderId);
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      LOG.severe("paymentDetails: storeId is null");
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling paymentDetails request");
    }
    
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      LOG.severe("paymentDetails: orderId is null");
      throw new ApiException(400, "Missing the required parameter 'orderId' when calling paymentDetails request");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/details/{storeId}/{orderId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId))
      .replaceAll("\\{" + "orderId" + "\\}", apiClient.escapeString(orderId));

    final TypeReference<PayResponse> typeRef = new TypeReference<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", null, typeRef);
  }

  /**
   * Get list of terminals.
   * 
   * @param storeId UUID of store (required)
   * @return TerminalsResponse
   * @throws ApiException if fails to make API call
   */
  public TerminalsResponse terminals(String storeId) throws ApiException {
    return kodyPayTerminalServiceTerminalsWithHttpInfo(storeId).getData();
  }

  ApiResponse<TerminalsResponse> kodyPayTerminalServiceTerminalsWithHttpInfo(String storeId) throws ApiException {
    LOG.finest("terminals: storeId=" + storeId);
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      LOG.severe("terminals: storeId is null");
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling terminals request");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/terminals/{storeId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId));

    final TypeReference<TerminalsResponse> typeRef = new TypeReference<TerminalsResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", null, typeRef);
  }

  /**
   * Refund a payment.
   *
   * @param storeId UUID of store (required)
   * @param orderId to identify the payment (order) (required)
   * @param body refund request (required)
   * @return RefundResponse
   * @throws ApiException if fails to make API call
   */
  public RefundResponse refund(String storeId, String orderId, RefundRequest body) throws ApiException {
    return kodyPayTerminalServiceRefundWithHttpInfo(storeId, orderId, body).getData();
  }

  ApiResponse<RefundResponse> kodyPayTerminalServiceRefundWithHttpInfo(String storeId, String orderId, RefundRequest body) throws ApiException {
    LOG.finest("refund: storeId=" + storeId + ", orderId=" + orderId + ", body=" + body);
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      LOG.severe("refund: storeId is null");
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling refund request");
    }

    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      LOG.severe("refund: orderId is null");
      throw new ApiException(400, "Missing the required parameter 'orderId' when calling refund request");
    }

    // verify the required parameter 'body' is set
    if (body == null) {
      LOG.severe("refund: body is null");
      throw new ApiException(400, "Missing the required parameter 'body' when calling refund request");
    }

    // create path and map variables
    String localVarPath = "/v1/pay/refund/{storeId}/{orderId}"
            .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId))
            .replaceAll("\\{" + "orderId" + "\\}", apiClient.escapeString(orderId));

    final TypeReference<RefundResponse> typeRef = new TypeReference<RefundResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", body, typeRef);
  }
}
