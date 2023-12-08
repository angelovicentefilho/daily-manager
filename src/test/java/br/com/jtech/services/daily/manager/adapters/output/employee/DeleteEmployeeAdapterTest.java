package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeAdapterTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private DeleteEmployeeAdapter deleteEmployeeAdapter;

    @Test
    void testDeleteByIdWhenCalledThenRepositoryDeleteByIdCalledWithGivenUUID() {
        UUID id = UUID.randomUUID();
        doNothing().when(employeeRepository).deleteById(id);
        deleteEmployeeAdapter.deleteById(id);
        verify(employeeRepository).deleteById(id);
    }

    @Test
    void testDeleteAllWhenCalledThenRepositoryDeleteAllCalled() {
        doNothing().when(employeeRepository).deleteAll();
        deleteEmployeeAdapter.deleteAll();
        verify(employeeRepository).deleteAll();
    }
}