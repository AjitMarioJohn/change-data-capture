package com.cdc.id.allotment.presentation.rest.api.controllers;

import com.cdc.id.allotment.presentation.rest.api.models.RestApiResponse;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface AllotmentResource {
    CompletableFuture<RestApiResponse<Map<String, String>>> allotInstance();
    CompletableFuture<RestApiResponse<Void>> deRegisterInstance(Map<String, String> request);
}
