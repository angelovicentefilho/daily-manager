package br.com.jtech.services.daily.manager.application.core.usecases.daily;

import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeBadRequestException;
import br.com.jtech.services.daily.manager.application.core.exceptions.employee.EmployeeNotFoundException;
import br.com.jtech.services.daily.manager.application.core.exceptions.squad.SquadNotFoundException;
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
                sendEmailOutputGateway
        );
        validEmployee = Employee.builder().email("test@test.com").build();
        validSquad = Squad.builder().id(GenId.newUuid()).build();
    }

    @Test
    public void testFinishWithValidDaily() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(Squad.builder().id(GenId.newUuid()).build());
        daily.setTasks(Arrays.asList(Task.builder().id(GenId.newUuid()).assignee(validEmployee).build()));

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));
        when(findSquadByIdOutputGateway.findById(any())).thenReturn(Optional.of(new Squad()));
        when(finishDailyOutputGateway.finish(any())).thenReturn(Optional.of(daily));

        // Act
        Optional<Daily> result = finishDailyUseCase.finish(daily);

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(daily);
        verify(sendEmailOutputGateway, times(1)).send(any());
    }

    @Test
    public void testFinishWithNullAuthor() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(null);
        daily.setSquad(new Squad());
        daily.setTasks(new ArrayList<>());

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Employee is required!");
        verifyNoInteractions(findEmployeeByEmailOutputGateway, findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithInvalidAuthorEmail() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(new Employee());
        daily.setSquad(validSquad);

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(EmployeeBadRequestException.class)
                .hasMessage("Email is invalid!");
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithNullSquad() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(null);
        daily.setTasks(new ArrayList<>());

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Squad is required!");
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
    }

    @Test
    public void testFinishWithInvalidSquadId() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(new Squad());
        daily.setTasks(new ArrayList<>());

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.of(new Employee()));

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(SquadNotFoundException.class)
                .hasMessage("Squad is invalid!");
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verifyNoInteractions(finishDailyOutputGateway, sendEmailOutputGateway);
    }

    @Test
    public void testFinishWithNullTasks() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(validSquad);
        daily.setTasks(null);

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
    }

    @Test
    public void testFinishWithEmptyTasks() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(validSquad);
        daily.setTasks(new ArrayList<>());

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
    }

    @Test
    public void testFinishWithValidTasks() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(validSquad);

        daily.setTasks(createTasks(validEmployee));

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
    }

    @Test
    public void testFinishWithInvalidTaskAssigneeEmail() {
        // Arrange
        Daily daily = new Daily();
        daily.setAuthor(validEmployee);
        daily.setSquad(validSquad);

        daily.setTasks(createTasks(new Employee()));

        when(findEmployeeByEmailOutputGateway.findByEmail(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> finishDailyUseCase.finish(daily))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Employee not found!");
        verify(findEmployeeByEmailOutputGateway, times(1)).findByEmail(anyString());
        verifyNoInteractions(findSquadByIdOutputGateway, finishDailyOutputGateway, sendEmailOutputGateway);
    }

    private List<Task> createTasks(final Employee employee) {
        final Task task1 = Task.builder().id(GenId.newUuid()).assignee(employee).build();
        final Task task2 = Task.builder().id(GenId.newUuid()).assignee(employee).build();
        return Arrays.asList(task1, task2);
    }
}