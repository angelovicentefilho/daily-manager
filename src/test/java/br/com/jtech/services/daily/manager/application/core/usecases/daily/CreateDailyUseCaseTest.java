package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeBadRequestException;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateDailyUseCaseTest {

    @Mock
    private CreateDailyOutputGateway createDailyOutputGateway;

    @Mock
    private FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    private CreateDailyUseCase createDailyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createDailyUseCase = new CreateDailyUseCase(createDailyOutputGateway, findEmployeeByUsernameOutputGateway, findSquadByIdOutputGateway);
    }

    @Test
    void testCreateWhenDailyValidThenReturnCreatedDaily() {
        var author = Employee.builder().username("username").build();
        var squad = Squad.builder().name("Squad 1").id(UUID.randomUUID()).build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .squad(squad)
                .build();

        when(createDailyOutputGateway.create(daily)).thenReturn(Optional.of(daily));
        when(findEmployeeByUsernameOutputGateway.findByUsername(author.getUsername())).thenReturn(Optional.of(author));
        when(findSquadByIdOutputGateway.findById(squad.getId())).thenReturn(Optional.of(squad));
        var createdDaily = createDailyUseCase.create(daily).get();
        assertEquals(daily, createdDaily);
        verify(createDailyOutputGateway, times(1)).create(daily);
        verify(findEmployeeByUsernameOutputGateway, times(1)).findByUsername(author.getUsername());
        verify(findSquadByIdOutputGateway, times(1)).findById(squad.getId());
    }

    @Test
    void testShouldNotCreateDailyWithoutAValidAuthor() {
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .squad(Squad.builder().name("Squad 1").id(UUID.randomUUID()).build())
                .build();
        assertThrows(EmployeeBadRequestException.class, () -> createDailyUseCase.create(daily));
        verify(createDailyOutputGateway, times(0)).create(daily);
    }

    @Test
    void testShouldNotCreateDailyWithoutAValidSquad() {
        var author = Employee.builder().username("username").build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .build();
        when(findEmployeeByUsernameOutputGateway.findByUsername(author.getUsername())).thenReturn(Optional.of(author));
        assertThrows(IllegalArgumentException.class, () -> createDailyUseCase.create(daily));
        verify(createDailyOutputGateway, times(0)).create(daily);
    }
}