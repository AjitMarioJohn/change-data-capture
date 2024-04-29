package com.cdc.id.allotment.core.usecases.deregister;

import com.cdc.id.allotment.commons.exceptions.NotFoundDomainException;
import com.cdc.id.allotment.core.usecases.UseCase;
import com.cdc.id.allotment.data.entities.Allotment;
import com.cdc.id.allotment.data.repositories.AllotmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class DeRegisterSchedularUseCase extends UseCase<DeRegisterSchedularUseCase.Input, DeRegisterSchedularUseCase.Output> {

    private final AllotmentRepository allotmentRepository;

    @Override
    public Output execute(Input input) {
        Objects.requireNonNull(input);
        Allotment allotment = allotmentRepository.findByInstanceId(input.getInstanceId());
        if (Objects.isNull(allotment)) {
            throw new NotFoundDomainException("No record found with instance id %s", input.getInstanceId());
        }
        allotment.setActive(false);
        allotmentRepository.save(allotment);
        return new Output(true);
    }

    @Value
    public static class Input implements UseCase.Input {
        String instanceId;
    }

    @Value
    public static class Output implements UseCase.Output {
        boolean deregistered;
    }
}
