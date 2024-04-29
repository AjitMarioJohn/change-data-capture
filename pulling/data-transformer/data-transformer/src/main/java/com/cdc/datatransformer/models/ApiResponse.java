package com.cdc.datatransformer.models;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private Map<String, String> data;
}
