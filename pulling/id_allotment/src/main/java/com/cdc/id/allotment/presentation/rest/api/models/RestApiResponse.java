package com.cdc.id.allotment.presentation.rest.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RestApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public static <T> RestApiResponse<T> empty() {
        return RestApiResponse.<T>builder()
                .success(false)
                .message("")
                .data(null)
                .build();
    }

    public static <T> RestApiResponse<T> success(T data) {
        return RestApiResponse.<T>builder()
                .success(true)
                .message("Operation successful")
                .data(data)
                .build();
    }

    public static <T> RestApiResponse<T> success(String message, T data) {
        return RestApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

}
