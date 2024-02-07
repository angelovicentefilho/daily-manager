package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.daily.DailyNotFoundException;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeBadRequestException;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
import br.com.jtech.services.daily.manager.application.ports.output.daily.CheckOpenDailyExistenceOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FinishDailyOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.daily.SendEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.squad.FindSquadByIdOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class FinishDailyUseCaseTest {

    private FinishDailyUseCase finishDailyUseCase;

    @Mock
    private FinishDailyOutputGateway finishDailyOutputGateway;

    @Mock
    private FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;

    @Mock
    private FindSquadByIdOutputGateway findSquadByIdOutputGateway;

    @Mock
    private CheckOpenDailyExistenceOutputGateway checkOpenDailyExistenceOutputGateway;

    @Mock
    private SendEmailOutputGateway sendEmailOutputGateway;

    private Employee validEmployee;
    private Squad validSquad;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        finishDailyUseCase = new FinishDailyUseCase(
                finishDailyOutputGateway,
                findEmployeeByEmailOutputGateway,
                findSquadByIdOutputGateway,
                checkOpenDailyExistenceOutputGateway,
                sendEmailOutputGateway
        );
        validEmployee = Employee.builder().email("test@test.com").build();
        validSquad = Squad.builder().id(GenId.newUuid()).build();
        when(checkOpenDailyExistenceOutputGateway.exists(any())).thenReturn(true);
    }

    @Test
    public void testFinishWithValidDaily() {
        // Arrange
        final var daily = Daily.builder()
                .author(validEmployee)
                .id(GenId.newUuid())
                .squad(validSquad)
                .tasks(Arrays.asList(Task.builder().id(GenId.newUuid()).assignee(validEmployee).build()))
                .build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));
        when(findSquadByIdOutputGateway.findById(any())).thenReturn(Optional.of(new Squad()));
        when(finishDailyOutputGateway.finish(any())).thenReturn(Optional.of(daily));

        // Act
        Optional<Daily> result = finishDailyUseCase.finish(daily);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(daily);
        verify(sendEmailOutputGateway, times(1)).send(any());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
    }


    @Test
    public void testFinishWithInvalidDailyId() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(validSquad).tasks(new ArrayList<>()).build();
        when(checkOpenDailyExistenceOutputGateway.exists(any())).thenReturn(false);

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(DailyNotFoundException.class)
                .hasMessage("Daily not found!");
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
        verifyNoInteractions(findEmployeeByEmailOutputGateway, findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithNullAuthor() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(validSquad).tasks(new ArrayList<>()).build();

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Employee is required!");
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
        verifyNoInteractions(findEmployeeByEmailOutputGateway, findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithInvalidAuthorEmail() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(validSquad).tasks(new ArrayList<>()).author(new Employee()).build();

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(EmployeeBadRequestException.class)
                .hasMessage("Email is invalid!");
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithNullSquad() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).author(validEmployee).tasks(new ArrayList<>()).build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Squad is required!");
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
    }

    @Test
    public void testFinishWithInvalidSquadId() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(new Squad()).author(validEmployee).tasks(new ArrayList<>()).build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(SquadNotFoundException.class)
                .hasMessage("Squad is invalid!");
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
        verifyNoInteractions(finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithNullTasks() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).author(validEmployee).squad(validSquad).tasks(null).build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));
        when(findSquadByIdOutputGateway.findById(any())).thenReturn(Optional.of(new Squad()));
        when(finishDailyOutputGateway.finish(any())).thenReturn(Optional.of(daily));

        // Act
        Optional<Daily> result = finishDailyUseCase.finish(daily);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(daily);
        verify(sendEmailOutputGateway, never()).send(any());
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verify(findSquadByIdOutputGateway, times(1)).findById(any());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
    }

    @Test
    public void testFinishWithEmptyTasks() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(validSquad).author(validEmployee).tasks(new ArrayList<>()).build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));
        when(findSquadByIdOutputGateway.findById(any())).thenReturn(Optional.of(new Squad()));
        when(finishDailyOutputGateway.finish(any())).thenReturn(Optional.of(daily));

        // Act
        Optional<Daily> result = finishDailyUseCase.finish(daily);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(daily);
        verify(sendEmailOutputGateway, never()).send(any());
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verify(findSquadByIdOutputGateway, times(1)).findById(any());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
    }

    @Test
    public void testFinishWithValidTasks() {
        // Arrange
        final var daily = Daily.builder().id(GenId.newUuid()).squad(validSquad).author(validEmployee).tasks(createTasks(validEmployee)).build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));
        when(findSquadByIdOutputGateway.findById(any())).thenReturn(Optional.of(new Squad()));
        when(finishDailyOutputGateway.finish(any())).thenReturn(Optional.of(daily));

        // Act
        Optional<Daily> result = finishDailyUseCase.finish(daily);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(daily);
        verify(sendEmailOutputGateway, times(2)).send(any());
        verify(findEmployeeByEmailOutputGateway, times(3)).findByEmail(anyString());
        verify(findSquadByIdOutputGateway, times(1)).findById(any());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
    }

    @Test
    public void testFinishWithInvalidTaskAssigneeEmail() {
        // Arrange
        final var daily = Daily.builder()
                .id(GenId.newUuid())
                .squad(validSquad)
                .author(validEmployee)
                .tasks(createTasks(new Employee()))
                .build();

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Employee not found!");
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verify(checkOpenDailyExistenceOutputGateway, times(1)).exists(any());
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    private List<Task> createTasks(final Employee employee) {
        final Task task1 = Task.builder().id(GenId.newUuid()).assignee(employee).build();
        final Task task2 = Task.builder().id(GenId.newUuid()).assignee(employee).build();
        return Arrays.asList(task1, task2);
    }
}