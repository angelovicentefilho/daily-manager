package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FinishDailyAdapterTest {

    private FinishDailyAdapter finishDailyAdapter;

    @Mock
    private DailyRepository dailyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        finishDailyAdapter = new FinishDailyAdapter(dailyRepository);
    }

    @Test
    public void testFinishWithValidDaily() {
        // Arrange
        Daily daily = Daily
                .builder()
                .author(Employee.builder().build())
                .squad(Squad.builder().build())
                .tasks(new ArrayList<>())
                .blockers(new ArrayList<>()).build();
        DailyDocument document = DailyDocument
                .builder()
                .author(EmployeeDocument.builder().build())
                .squad(SquadDocument.builder().build())
                .tasks(new ArrayList<>())
                .blockers(new ArrayList<>()).build();

        when(dailyRepository.save(any())).thenReturn(document);

        // Act
        Optional<Daily> result = finishDailyAdapter.finish(daily);

        // Assert
        assertThat(result).isPresent();
        verify(dailyRepository, times(1)).save(any());
    }

    @Test
    public void testFinishWithNullDaily() {
        // Act
        Optional<Daily> result = finishDailyAdapter.finish(null);

        // Assert
        assertThat(result).isEmpty();
        verifyNoInteractions(dailyRepository);
    }
}