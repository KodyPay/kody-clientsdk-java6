package com.kodypay.api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.kodypay.api.model.ApiException;
import com.kodypay.api.model.ApiResponse;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApiClient {
  private static final Logger LOG = Logger.getLogger(ApiClient.class.getName());
  private final Map<String, String> defaultHeaderMap = new HashMap<String, String>();
  private final HttpClient httpClient;
  private final JSON json;
  private final String basePath;

  public ApiClient(String basePath, String apiKey) {
    json = new JSON();
    httpClient = HttpClientBuilder.create().setUserAgent("KodyPay").build();
    this.basePath = basePath;
    defaultHeaderMap.put("Accept", "application/json");
    defaultHeaderMap.put("Content-Type", "application/json");
    if (apiKey != null) {
      defaultHeaderMap.put("X-API-Key", apiKey);
    }
  }

  /**
   * Escape the given string to be used as URL query value.
   * @param str String
   * @return Escaped string
   */
  public String escapeString(String str) {
    try {
      return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
    } catch (UnsupportedEncodingException e) {
      return str;
    }
  }

  public <T> ApiResponse<T> invokeAPI(String path, String method, Object body, final TypeReference<T> typeRef) throws ApiException {
    LOG.finest("invokeAPI: " + method + ": " + path);
    final HttpUriRequest request = getRequest(method, path, body);
    for (Map.Entry<String, String> entry : defaultHeaderMap.entrySet()) {
      request.addHeader(entry.getKey(), entry.getValue());
    }

    ApiResponse<T> response;
    try {
      response = httpClient.execute(request, new ResponseHandler<ApiResponse<T>>() {
        @Override
        public ApiResponse<T> handleResponse(HttpResponse response) throws IOException {
          int status = response.getStatusLine().getStatusCode();
          LOG.finest("Response status: " + status + " " + response.getStatusLine().getReasonPhrase());
          if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            String body = entity != null ? EntityUtils.toString(entity) : null;
            if (body != null && !body.isEmpty()) {
              LOG.finest("Response body: " + body);
              T data = json.getMapper().readValue(body, typeRef);
              return new ApiResponse<T>(status, buildResponseHeaders(response), data);
            } else {
              return new ApiResponse<T>(status, buildResponseHeaders(response));
            }
          } else {
            HttpEntity entity = response.getEntity();
            String body = entity != null ? EntityUtils.toString(entity) : null;
            LOG.finest("Unexpected response status: " + status + " " + response.getStatusLine().getReasonPhrase() + " " + body);
            throw new IOException("Unexpected response status: " + status);
          }
        }
      });
    } catch (IOException e) {
      LOG.finest("Exception while processing response: " + e.getMessage());
      throw new ApiException("Exception while processing response", e, 500);
    }
    return response;
  }

  private Map<String, String> buildResponseHeaders(HttpResponse response) {
    Map<String, String> responseHeaders = new HashMap<String, String>();
    HeaderIterator headers = response.headerIterator();
    while (headers.hasNext()) {
      Header header = headers.nextHeader();
      responseHeaders.put(header.getName(), header.getValue());
    }
    LOG.finest("Response headers: " + responseHeaders);
    return responseHeaders;
  }

  private HttpUriRequest getRequest(String method, String path, Object body) throws ApiException {
    final HttpUriRequest request;

    if (method.equals("GET")) {
      request = new HttpGet(basePath + path);
    } else if (method.equals("DELETE")) {
      request = new HttpDelete(basePath + path);
    } else if (method.equals("HEAD")) {
      request = new HttpHead(basePath + path);
    } else if (method.equals("POST")) {
      HttpPost post = new HttpPost(basePath + path);
      encodeBody(body, post);
      request = post;
    } else if (method.equals("PATCH")) {
      HttpPatch patch = new HttpPatch(basePath + path);
      encodeBody(body, patch);
      request = patch;
    } else if (method.equals("PUT")) {
      HttpPut put = new HttpPut(basePath + path);
      encodeBody(body, put);
      request = put;
    } else {
      throw new ApiException(500, "unknown method type " + method);
    }
    return request;
  }

  private void encodeBody(Object body, HttpEntityEnclosingRequest request) throws ApiException {
    if (body != null) {
      try {
        request.setEntity(new StringEntity(json.getMapper().writeValueAsString(body), "UTF-8"));
      } catch (JsonProcessingException e) {
        throw new ApiException("Exception while encoding request body", e, 500);
      }
    }
  }
}
