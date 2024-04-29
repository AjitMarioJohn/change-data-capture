package com.cdc.id.allotment.core.usecases.register;

import com.cdc.id.allotment.core.usecases.UseCase;
import com.cdc.id.allotment.data.entities.Allotment;
import com.cdc.id.allotment.data.repositories.AllotmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RegisterSchedularUseCase extends UseCase<RegisterSchedularUseCase.Input, RegisterSchedularUseCase.Output> {

    private final AllotmentRepository allotmentRepository;

    @Override
    public RegisterSchedularUseCase.Output execute(RegisterSchedularUseCase.Input input) {
        List<Allotment> inactiveAllotment = allotmentRepository.fetchInactiveInstance();
        String instanceId = null;
        Allotment allotment;
        if (Objects.isNull(inactiveAllotment) || inactiveAllotment.isEmpty()) {
            log.debug("No inactive instance found. Creating new instance id");
            instanceId = UUID.randomUUID().toString();
            allotment = new Allotment();
            allotment.setInstanceId(instanceId);
        } else {
            log.debug("Inactive instances found alloting one of them");
            allotment = inactiveAllotment.getFirst();
            instanceId = allotment.getInstanceId();
        }
        allotment.setActive(true);

        allotmentRepository.save(allotment);
        return new Output(instanceId);
    }

    @Value
    public static class Input implements UseCase.Input{
    }
    @Value
    public static class Output implements UseCase.Output{
        String allotedId;
    }
}
