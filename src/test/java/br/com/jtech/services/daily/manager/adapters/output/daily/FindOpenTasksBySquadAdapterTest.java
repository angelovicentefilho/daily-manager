package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.BlockerDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.TaskDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Blocker;
import br.com.jtech.services.daily.manager.application.core.domains.daily.StatusBlocker;
import br.com.jtech.services.daily.manager.application.core.domains.daily.StatusTask;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FindOpenTasksBySquadAdapterTest {

    private FindOpenTasksBySquadAdapter findOpenTasksBySquadAdapter;

    @Mock
    private DailyRepository dailyRepository;

    @BeforeEach
    public void setUp() {
        findOpenTasksBySquadAdapter = new FindOpenTasksBySquadAdapter(dailyRepository);
    }

    @Test
    public void testFindBySquadWhenTasksExistThenReturnBlockers() {
        // Arrange
        String squad = "Test Squad";
        List<TaskDocument> tasks = new ArrayList<>();
        tasks.add(TaskDocument.builder().title("Task 1").status(StatusTask.PENDING.name()).assignee(EmployeeDocument.builder().name("Employee 1").build()).priority("HIGH").build());
        tasks.add(TaskDocument.builder().title("Task 2").status(StatusTask.COMPLETED.name()).assignee(EmployeeDocument.builder().name("Employee 1").build()).priority("HIGH").build());
        tasks.add(TaskDocument.builder().title("Task 3").status(StatusTask.IN_PROGRESS.name()).assignee(EmployeeDocument.builder().name("Employee 1").build()).priority("HIGH").build());
        final var daily = DailyDocument.builder().squad(SquadDocument.builder().name(squad).build()).tasks(tasks).build();
        when(dailyRepository.findTopBySquad_NameOrderByCreateAtDesc(squad)).thenReturn(Optional.of(daily));

        // Act
        List<Task> result = findOpenTasksBySquadAdapter.findBySquad(squad);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Task 1");
        assertThat(result.get(1).getTitle()).isEqualTo("Task 3");
        verify(dailyRepository, times(1)).findTopBySquad_NameOrderByCreateAtDesc(squad);
    }

    @Test
    public void testFindBySquadWhenNoTasksExistThenReturnEmptyList() {
        // Arrange
        String squad = "Test Squad";
        when(dailyRepository.findTopBySquad_NameOrderByCreateAtDesc(squad)).thenReturn(Optional.of(new DailyDocument()));

        // Act
        List<Task> result = findOpenTasksBySquadAdapter.findBySquad(squad);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        verify(dailyRepository, times(1)).findTopBySquad_NameOrderByCreateAtDesc(squad);
    }

    @Test
    public void testFindBySquadWhenSquadDoesNotExistThenReturnEmptyList() {
        // Arrange
        String squad = "Nonexistent Squad";
        when(dailyRepository.findTopBySquad_NameOrderByCreateAtDesc(squad)).thenReturn(Optional.empty());

        // Act
        List<Task> result = findOpenTasksBySquadAdapter.findBySquad(squad);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        verify(dailyRepository, times(1)).findTopBySquad_NameOrderByCreateAtDesc(squad);
    }
}