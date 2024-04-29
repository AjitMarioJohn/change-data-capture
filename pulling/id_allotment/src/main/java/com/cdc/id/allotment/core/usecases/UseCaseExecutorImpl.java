package com.cdc.id.allotment.core.usecases;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor{
    @Override
    public <R, I extends UseCase.Input, O extends UseCase.Output> CompletableFuture<R> execute(UseCase<I, O> usecase, I input, Function<O, R> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(usecase :: execute)
                .thenApplyAsync(outputMapper);
    }
}
