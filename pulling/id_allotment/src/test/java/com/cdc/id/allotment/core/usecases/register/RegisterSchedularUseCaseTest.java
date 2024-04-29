package com.cdc.id.allotment.core.usecases.register;

import com.cdc.id.allotment.data.repositories.AllotmentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.event.LoggingEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class RegisterSchedularUseCaseTest {

    @Mock
    private AllotmentRepository allotmentRepository;
    @Captor
    private ArgumentCaptor<LoggingEvent> loggingEventCaptor;
    @InjectMocks
    private RegisterSchedularUseCase sut;

    @Test
    @Disabled
    public void testExecute_whenNoInactiveInstance_Present() {
        RegisterSchedularUseCase.Output output = sut.execute(new RegisterSchedularUseCase.Input());
        assertNotNull(output);
//        assertEquals();
    }

}