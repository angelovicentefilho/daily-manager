package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Email;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CreateDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
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
    private FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @Mock
    private SendEmailOutputGateway sendEmailOutputGateway;

    private CreateDailyUseCase createDailyUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createDailyUseCase = new CreateDailyUseCase(createDailyOutputGateway, findEmployeeByUsernameOutputGateway, findSquadByIdOutputGateway, sendEmailOutputGateway);
    }

    @Test
    void testCreateWhenDailyValidThenReturnCreatedDaily() {
        var assignee = Employee.builder().username("assignee").build();
        var task = Task.builder().assignee(assignee).build();
        var author = Employee.builder().username("author").build();
        var squad = Squad.builder().name("Squad 1").id(UUID.randomUUID()).build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .squad(squad)
                .tasks(Arrays.asList(task))
                .build();

        when(createDailyOutputGateway.create(daily)).thenReturn(Optional.of(daily));
        when(findEmployeeByUsernameOutputGateway.findByUsername(author.getUsername())).thenReturn(Optional.of(author));
        when(findEmployeeByUsernameOutputGateway.findByUsername(assignee.getUsername())).thenReturn(Optional.of(assignee));
        when(findSquadByIdOutputGateway.findById(squad.getId())).thenReturn(Optional.of(squad));
        var createdDaily = createDailyUseCase.create(daily).get();
        assertEquals(daily, createdDaily);
        verify(createDailyOutputGateway, times(1)).create(daily);
        verify(findEmployeeByUsernameOutputGateway, times(1)).findByUsername(author.getUsername());
        verify(findEmployeeByUsernameOutputGateway, times(1)).findByUsername(assignee.getUsername());
        verify(findSquadByIdOutputGateway, times(1)).findById(squad.getId());
        verify(sendEmailOutputGateway, times(1)).send(any(Email.class));
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
    void testShouldNotCreateDailyWithoutAValidAssigneeOnTask() {
        var author = Employee.builder().username("author").build();
        var squad = Squad.builder().name("Squad 1").id(UUID.randomUUID()).build();
        var daily = Daily.builder()
                .id(UUID.randomUUID())
                .summary("Daily 1")
                .author(author)
                .tasks(Arrays.asList(Task.builder().build()))
                .squad(squad)
                .build();
        when(findEmployeeByUsernameOutputGateway.findByUsername(author.getUsername())).thenReturn(Optional.of(author));
        when(findSquadByIdOutputGateway.findById(squad.getId())).thenReturn(Optional.of(squad));
        assertThrows(NullPointerException.class, () -> createDailyUseCase.create(daily));
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
        assertThrows(NullPointerException.class, () -> createDailyUseCase.create(daily));
        verify(createDailyOutputGateway, times(0)).create(daily);
    }
}