package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindOpenBlockersBySquadOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindOpenTasksBySquadOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateDailyUseCaseTest {

    @Mock
    private CreateDailyOutputGateway createDailyOutputGateway;

    @Mock
    private FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @Mock
    private FindOpenBlockersBySquadOutputGateway findOpenBlockersBySquadOutputGateway;

    @Mock
    private FindOpenTasksBySquadOutputGateway findOpenTasksBySquadOutputGateway;

    private CreateDailyUseCase createDailyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createDailyUseCase = new CreateDailyUseCase(createDailyOutputGateway, findEmployeeByEmailOutputGateway, findSquadByIdOutputGateway, findOpenTasksBySquadOutputGateway, findOpenBlockersBySquadOutputGateway);
    }

    @Test
    void testCreateWhenDailyValidThenReturnCreatedDaily() {
        var author = Employee.builder().email("test@test.com").build();
        var squad = Squad.builder().name("Squad 1").id(UUID.randomUUID()).build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .squad(squad)
                .build();

        when(createDailyOutputGateway.create(daily)).thenReturn(Optional.of(daily));
        when(findEmployeeByEmailOutputGateway.findByEmail(author.getEmail())).thenReturn(Optional.of(author));
        when(findSquadByIdOutputGateway.findById(squad.getId())).thenReturn(Optional.of(squad));
        var createdDaily = createDailyUseCase.create(daily).get();
        assertEquals(daily, createdDaily);
        verify(createDailyOutputGateway, times(1)).create(daily);
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(author.getEmail());
        verify(findSquadByIdOutputGateway, times(1)).findById(squad.getId());
        verify(findOpenTasksBySquadOutputGateway, times(1)).findBySquad(squad.getName());
        verify(findOpenBlockersBySquadOutputGateway, times(1)).findBySquad(squad.getName());
    }

    @Test
    void testShouldNotCreateDailyWithoutAValidAuthor() {
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .squad(Squad.builder().name("Squad 1").id(UUID.randomUUID()).build())
                .build();
        assertThrows(NullPointerException.class, () -> createDailyUseCase.create(daily));
        verify(createDailyOutputGateway, times(0)).create(daily);
    }

    @Test
    void testShouldNotCreateDailyWithoutAValidSquad() {
        var author = Employee.builder().email("test@test.com").build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .build();
        when(findEmployeeByEmailOutputGateway.findByEmail(author.getEmail())).thenReturn(Optional.of(author));
        assertThrows(NullPointerException.class, () -> createDailyUseCase.create(daily));
        verify(createDailyOutputGateway, times(0)).create(daily);
    }
}