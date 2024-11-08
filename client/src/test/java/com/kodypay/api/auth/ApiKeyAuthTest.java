package com.kodypay.api.auth;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import com.kodypay.api.model.Pair;
import org.junit.*;
import static org.junit.Assert.*;


public class ApiKeyAuthTest {
    @Test
    public void testApplyToParamsInHeaderWithPrefix() {
        List<Pair> queryParams = new ArrayList<Pair>();
        Map<String, String> headerParams = new HashMap<String, String>();

        ApiKeyAuth auth = new ApiKeyAuth("header", "X-API-Key");
        auth.setApiKey("my-api-key-token");
        auth.applyToParams(queryParams, headerParams);

        // no changes to query parameters
        assertEquals(0, queryParams.size());
        assertEquals(1, headerParams.size());
        assertEquals("my-api-key-token", headerParams.get("X-API-Key"));
    }
}
