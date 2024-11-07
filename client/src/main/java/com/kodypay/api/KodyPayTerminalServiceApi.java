package com.kodypay.api;

import javax.ws.rs.core.GenericType;

import com.kodypay.api.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KodyPayTerminalServiceApi {
  private ApiClient apiClient;

  public KodyPayTerminalServiceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public KodyPayTerminalServiceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param terminalId to identify the terminal where the payment was sent (required)
   * @param body  (required)
   * @return V1CancelResponse
   * @throws ApiException if fails to make API call
   */
  public CancelResponse kodyPayTerminalServiceCancel(String storeId, String terminalId, CancelRequest body) throws ApiException {
    return kodyPayTerminalServiceCancelWithHttpInfo(storeId, terminalId, body).getData();
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param terminalId to identify the terminal where the payment was sent (required)
   * @param body  (required)
   * @return ApiResponse&lt;V1CancelResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<CancelResponse> kodyPayTerminalServiceCancelWithHttpInfo(String storeId, String terminalId, CancelRequest body) throws ApiException {
    Object localVarPostBody = body;
    
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


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "x_api_key" };

    GenericType<CancelResponse> localVarReturnType = new GenericType<CancelResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param terminalId send the payment to this terminal serial number (required)
   * @param body  (required)
   * @return PayResponse
   * @throws ApiException if fails to make API call
   */
  public PayResponse kodyPayTerminalServicePay(String storeId, String terminalId, PayRequest body) throws ApiException {
    return kodyPayTerminalServicePayWithHttpInfo(storeId, terminalId, body).getData();
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param terminalId send the payment to this terminal serial number (required)
   * @param body  (required)
   * @return ApiResponse&lt;PayResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<PayResponse> kodyPayTerminalServicePayWithHttpInfo(String storeId, String terminalId, PayRequest body) throws ApiException {
    Object localVarPostBody = body;
    
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


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "x_api_key" };

    GenericType<PayResponse> localVarReturnType = new GenericType<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param orderId to identify the payment (order) (required)
   * @return V1PayResponse
   * @throws ApiException if fails to make API call
   */
  public PayResponse kodyPayTerminalServicePaymentDetails(String storeId, String orderId) throws ApiException {
    return kodyPayTerminalServicePaymentDetailsWithHttpInfo(storeId, orderId).getData();
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @param orderId to identify the payment (order) (required)
   * @return ApiResponse&lt;V1PayResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<PayResponse> kodyPayTerminalServicePaymentDetailsWithHttpInfo(String storeId, String orderId) throws ApiException {
    Object localVarPostBody = null;
    
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


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "x_api_key" };

    GenericType<PayResponse> localVarReturnType = new GenericType<PayResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @return V1TerminalsResponse
   * @throws ApiException if fails to make API call
   */
  public TerminalsResponse kodyPayTerminalServiceTerminals(String storeId) throws ApiException {
    return kodyPayTerminalServiceTerminalsWithHttpInfo(storeId).getData();
  }

  /**
   * 
   * 
   * @param storeId UUID of store (required)
   * @return ApiResponse&lt;V1TerminalsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<TerminalsResponse> kodyPayTerminalServiceTerminalsWithHttpInfo(String storeId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'storeId' is set
    if (storeId == null) {
      throw new ApiException(400, "Missing the required parameter 'storeId' when calling kodyPayTerminalServiceTerminals");
    }
    
    // create path and map variables
    String localVarPath = "/v1/pay/terminals/{storeId}"
      .replaceAll("\\{" + "storeId" + "\\}", apiClient.escapeString(storeId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "x_api_key" };

    GenericType<TerminalsResponse> localVarReturnType = new GenericType<TerminalsResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
