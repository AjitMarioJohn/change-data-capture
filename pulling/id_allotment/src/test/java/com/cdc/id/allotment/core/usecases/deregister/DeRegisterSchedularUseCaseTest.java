package com.cdc.id.allotment.core.usecases.deregister;

import com.cdc.id.allotment.commons.exceptions.NotFoundDomainException;
import com.cdc.id.allotment.data.entities.Allotment;
import com.cdc.id.allotment.data.repositories.AllotmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeRegisterSchedularUseCaseTest {

    @Mock
    private AllotmentRepository allotmentRepository;
    @InjectMocks
    private DeRegisterSchedularUseCase sut;

    @Test
    public void testExecute_whenInputIsNull() {
        assertThrows(NullPointerException.class, () -> sut.execute(null));
    }

    @Test
    public void testExecute_whenEntityIsNotPresent() {
        DeRegisterSchedularUseCase.Input input = new DeRegisterSchedularUseCase.Input("InstanceId");
        assertThrows(NotFoundDomainException.class, () -> sut.execute(input));
    }

    @Test
    public void testExecute() {
        Allotment allotment = mock(Allotment.class);
        when(allotmentRepository.findByInstanceId(anyString())).thenReturn(allotment);

        DeRegisterSchedularUseCase.Input input = new DeRegisterSchedularUseCase.Input("InstanceId");
        DeRegisterSchedularUseCase.Output output = sut.execute(input);

        assertNotNull(output);
        assertTrue(output.isDeregistered());
        verify(allotment, atLeastOnce()).setActive(false);
        verify(allotmentRepository, atLeastOnce()).save(allotment);
    }
}