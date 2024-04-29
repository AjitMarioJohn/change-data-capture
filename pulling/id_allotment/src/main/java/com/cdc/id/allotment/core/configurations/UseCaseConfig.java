package com.cdc.id.allotment.core.configurations;

import com.cdc.id.allotment.core.usecases.deregister.DeRegisterSchedularUseCase;
import com.cdc.id.allotment.core.usecases.register.RegisterSchedularUseCase;
import com.cdc.id.allotment.data.repositories.AllotmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public RegisterSchedularUseCase registerSchedularUseCase(AllotmentRepository allotmentRepository) {
        return new RegisterSchedularUseCase(allotmentRepository);
    }

    @Bean
    public DeRegisterSchedularUseCase deRegisterSchedularUseCase(AllotmentRepository allotmentRepository) {
        return new DeRegisterSchedularUseCase(allotmentRepository);
    }
}
