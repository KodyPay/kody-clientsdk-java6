package com.kodypay.api;

import com.kodypay.api.client.ApiClient;
import com.kodypay.api.model.*;

import javax.ws.rs.core.GenericType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * KodyPayTerminalService provides methods to interact with the KodyPay API for handling terminal-related operations.
 */
public class KodyPayTerminalService {
  private final ApiClient apiClient;

  public KodyPayTerminalService() {
    apiClient = new ApiClient();
  }

  public KodyPayTerminalService(String basePath, String apiKey) {
    apiClient = new ApiClient()
            .setBasePath(basePath)
            .setApiKey(apiKey);
  }

  public ApiClient getApiClient() {
    return apiClient;
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
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling kodyPayTerminalServiceCancel");
    }
    
    // verify the required parameter 'terminalId' is set
    if (terminalId == null) {
      throw new ApiException(400, "Missing the required parameter 'terminalId' when calling kodyPayTerminalServiceCancel");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling kodyPayTerminalServiceCancel");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/cancel/{storeId}/{terminalId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId.toString()))
      .replaceAll("\\{" + "terminalId" + "\\}", apiClient.escapeString(terminalId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String localVarAccept = apiClient.selectHeaderAccept(new String[] { "application/json" });
    final String localVarContentType = apiClient.selectHeaderContentType(new String[] { "application/json" });
    final String[] localVarAuthNames = { "x_api_key" };

    GenericType<CancelResponse> localVarReturnType = new GenericType<CancelResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling kodyPayTerminalServicePay");
    }
    
    // verify the required parameter 'terminalId' is set
    if (terminalId == null) {
      throw new ApiException(400, "Missing the required parameter 'terminalId' when calling kodyPayTerminalServicePay");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling kodyPayTerminalServicePay");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/payment/{storeId}/{terminalId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId.toString()))
      .replaceAll("\\{" + "terminalId" + "\\}", apiClient.escapeString(terminalId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String localVarAccept = apiClient.selectHeaderAccept(new String[] { "application/json" });
    final String localVarContentType = apiClient.selectHeaderContentType(new String[] { "application/json" });
    final String[] localVarAuthNames = { "x_api_key" };

    GenericType<PayResponse> localVarReturnType = new GenericType<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling kodyPayTerminalServicePaymentDetails");
    }
    
    // verify the required parameter 'orderId' is set
    if (orderId == null) {
      throw new ApiException(400, "Missing the required parameter 'orderId' when calling kodyPayTerminalServicePaymentDetails");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/details/{storeId}/{orderId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId.toString()))
      .replaceAll("\\{" + "orderId" + "\\}", apiClient.escapeString(orderId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String localVarAccept = apiClient.selectHeaderAccept(new String[] { "application/json" });
    final String localVarContentType = apiClient.selectHeaderContentType(new String[] { "application/json" });
    final String[] localVarAuthNames = { "x_api_key" };

    GenericType<PayResponse> localVarReturnType = new GenericType<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling kodyPayTerminalServiceTerminals");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/terminals/{storeId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    final String localVarAccept = apiClient.selectHeaderAccept(new String[] { "application/json" });
    final String localVarContentType = apiClient.selectHeaderContentType(new String[] { "application/json" });
    final String[] localVarAuthNames = { "x_api_key" };

    GenericType<TerminalsResponse> localVarReturnType = new GenericType<TerminalsResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
