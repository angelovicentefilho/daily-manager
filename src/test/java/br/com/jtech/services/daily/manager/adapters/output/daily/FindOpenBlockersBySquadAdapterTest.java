package br.com.jtech.services.daily.manager.adapters.output.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.DailyRepository;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.BlockerDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Blocker;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Daily;
import br.com.jtech.services.daily.manager.application.core.domains.daily.StatusBlocker;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.application.ports.output.daily.FindOpenBlockersBySquadOutputGateway;
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
public class FindOpenBlockersBySquadAdapterTest {

    private FindOpenBlockersBySquadAdapter findOpenBlockersBySquadAdapter;

    @Mock
    private DailyRepository dailyRepository;

    @BeforeEach
    public void setUp() {
        findOpenBlockersBySquadAdapter = new FindOpenBlockersBySquadAdapter(dailyRepository);
    }

    @Test
    public void testFindBySquadWhenBlockersExistThenReturnBlockers() {
        // Arrange
        String squad = "Test Squad";
        List<BlockerDocument> blockers = new ArrayList<>();
        blockers.add(BlockerDocument.builder().descriptor("Blocker 1").status(StatusBlocker.OPEN.name()).build());
        blockers.add(BlockerDocument.builder().descriptor("Blocker 2").status(StatusBlocker.RESOLVED.name()).build());
        blockers.add(BlockerDocument.builder().descriptor("Blocker 3").status(StatusBlocker.IN_PROGRESS.name()).build());
        final var daily = DailyDocument.builder().squad(SquadDocument.builder().name(squad).build()).blockers(blockers).build();
        when(dailyRepository.findTopBySquad_NameOrderByCreateAtDesc(squad)).thenReturn(Optional.of(daily));

        // Act
        List<Blocker> result = findOpenBlockersBySquadAdapter.findBySquad(squad);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getDescriptor()).isEqualTo("Blocker 1");
        assertThat(result.get(1).getDescriptor()).isEqualTo("Blocker 3");
        verify(dailyRepository, times(1)).findTopBySquad_NameOrderByCreateAtDesc(squad);
    }

    @Test
    public void testFindBySquadWhenNoBlockersExistThenReturnEmptyList() {
        // Arrange
        String squad = "Test Squad";
        when(dailyRepository.findTopBySquad_NameOrderByCreateAtDesc(squad)).thenReturn(Optional.of(new DailyDocument()));

        // Act
        List<Blocker> result = findOpenBlockersBySquadAdapter.findBySquad(squad);

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
        List<Blocker> result = findOpenBlockersBySquadAdapter.findBySquad(squad);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
        verify(dailyRepository, times(1)).findTopBySquad_NameOrderByCreateAtDesc(squad);
    }
}