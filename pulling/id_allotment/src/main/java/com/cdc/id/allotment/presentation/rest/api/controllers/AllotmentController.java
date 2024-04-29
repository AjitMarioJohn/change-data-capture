package com.cdc.id.allotment.presentation.rest.api.controllers;

import com.cdc.id.allotment.core.usecases.UseCaseExecutor;
import com.cdc.id.allotment.core.usecases.deregister.DeRegisterSchedularUseCase;
import com.cdc.id.allotment.core.usecases.register.RegisterSchedularUseCase;
import com.cdc.id.allotment.presentation.rest.api.models.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AllotmentController implements AllotmentResource {

    private final RegisterSchedularUseCase registerSchedularUseCase;
    private final DeRegisterSchedularUseCase deRegisterSchedularUseCase;
    private final UseCaseExecutor useCaseExecutor;

    @PostMapping
    @Override
    @Operation(
            summary = "Register application",
            description = "Register the application and allot a instance id to it."
    )
    @ApiResponse(responseCode = "200", description = "Successfully registered", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CompletableFuture.class))
    })
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public CompletableFuture<RestApiResponse<Map<String, String>>> allotInstance() {
        return useCaseExecutor.execute(
                registerSchedularUseCase,
                new RegisterSchedularUseCase.Input(),
                (output) -> RestApiResponse.success(Map.of("id", output.getAllotedId()))
        );
    }

    @PutMapping
    @Override
    @Operation(
            summary = "Deregister application",
            description = "De-register the application"
    )
    @ApiResponse(responseCode = "200", description = "Successfully de-registered", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CompletableFuture.class))
    })
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public CompletableFuture<RestApiResponse<Void>> deRegisterInstance(@RequestBody Map<String, String> request) {
        return useCaseExecutor.execute(
                deRegisterSchedularUseCase,
                new DeRegisterSchedularUseCase.Input(request.getOrDefault("instanceId", StringUtils.EMPTY)),
                (output) -> RestApiResponse.success("DeRegistered", null)
        );
    }
}
