package br.com.jtech.services.daily.manager.adapters.output.repositories;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SquadRepositoryTest {

    @Mock
    private SquadRepository squadRepository;

    @Test
    public void testFindByIdWhenIdExistsThenReturnSquadDocument() {
        UUID id = UUID.randomUUID();
        SquadDocument expectedSquad = new SquadDocument();
        expectedSquad.setId(id);
        when(squadRepository.findById(id)).thenReturn(Optional.of(expectedSquad));
        Optional<SquadDocument> result = squadRepository.findById(id);
        assertTrue(result.isPresent(), "Squad should be found");
        assertEquals(expectedSquad, result.get(), "The returned squad should match the expected one");
    }
}
