package com.cdc.id.allotment.core.usecases;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface UseCaseExecutor {
    <R, I extends UseCase.Input, O extends UseCase.Output> CompletableFuture<R> execute(
            UseCase<I, O> usecase,
            I input,
            Function<O, R> outputMapper
    );
}
