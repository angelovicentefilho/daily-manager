package br.com.jtech.services.daily.manager.adapters.input.protocols.squad;

import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SquadResponseTest {

    private final UUID ID = UUID.randomUUID();
    private final UUID ID2 = UUID.randomUUID();

    @Test
    public void testFromDomain() {
        Squad squad = Squad.builder()
                .id(ID)
                .name("Alpha Squad")
                .description("A squad focused on alpha features")
                .maxCapacity(10)
                .isPublic(true)
                .build();

        SquadResponse squadResponse = SquadResponse.fromDomain(squad);

        assertThat(squadResponse).isNotNull();
        assertEquals(squad.getId().toString(), squadResponse.getId());
        assertEquals(squad.getName(), squadResponse.getName());
        assertEquals(squad.getDescription(), squadResponse.getDescription());
        assertEquals(squad.getMaxCapacity(), squadResponse.getMaxCapacity());
        assertEquals(squad.getIsPublic(), squadResponse.getIsPublic());
    }

    @Test
    public void testFromDomains() {
        Squad squad1 = Squad.builder()
                .id(ID)
                .name("Alpha Squad")
                .description("A squad focused on alpha features")
                .maxCapacity(10)
                .isPublic(true)
                .build();
        Squad squad2 = Squad.builder()
                .id(ID2)
                .name("Beta Squad")
                .description("A squad focused on beta features")
                .maxCapacity(8)
                .isPublic(false)
                .build();
        List<Squad> squads = List.of(squad1, squad2);

        SquadResponse squadResponse = SquadResponse.fromDomains(squads);

        assertThat(squadResponse).isNotNull();
        assertThat(squadResponse.getSquads()).hasSize(squads.size());
        assertNull(squadResponse.getId());
        assertNull(squadResponse.getName());
        assertNull(squadResponse.getDescription());
        assertNull(squadResponse.getMaxCapacity());
        assertNull(squadResponse.getIsPublic());
        assertEquals(squad1.getId().toString(), squadResponse.getSquads().get(0).getId());
        assertEquals(squad1.getName(), squadResponse.getSquads().get(0).getName());
        assertEquals(squad1.getDescription(), squadResponse.getSquads().get(0).getDescription());
        assertEquals(squad1.getMaxCapacity(), squadResponse.getSquads().get(0).getMaxCapacity());
        assertEquals(squad1.getIsPublic(), squadResponse.getSquads().get(0).getIsPublic());
        assertEquals(squad2.getId().toString(), squadResponse.getSquads().get(1).getId());
        assertEquals(squad2.getName(), squadResponse.getSquads().get(1).getName());
        assertEquals(squad2.getDescription(), squadResponse.getSquads().get(1).getDescription());
        assertEquals(squad2.getMaxCapacity(), squadResponse.getSquads().get(1).getMaxCapacity());
        assertEquals(squad2.getIsPublic(), squadResponse.getSquads().get(1).getIsPublic());
    }
}