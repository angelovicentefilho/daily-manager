package br.com.jtech.services.daily.manager.application.core.domains.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SquadTest {

    private SquadDocument squadDocument;
    private SquadRequest squadRequest;
    private Squad squad;

    @BeforeEach
    void setUp() {
        squadDocument = SquadDocument.builder()
                .id(UUID.randomUUID())
                .name("Alpha Team")
                .description("A squad of elite members.")
                .maxCapacity(5)
                .isPublic(true)
                .build();

        squadRequest = SquadRequest.builder()
                .id(squadDocument.getId().toString())
                .name(squadDocument.getName())
                .description(squadDocument.getDescription())
                .maxCapacity(squadDocument.getMaxCapacity())
                .isPublic(squadDocument.getIsPublic())
                .build();

        squad = new Squad();
        BeanUtils.copyProperties(squadDocument, squad);
    }

    @Test
    void testFromDocumentWhenValidSquadDocumentThenReturnSquad() {
        // Act
        Squad result = Squad.fromDocument(squadDocument);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(squadDocument.getId());
        assertThat(result.getName()).isEqualTo(squadDocument.getName());
        assertThat(result.getDescription()).isEqualTo(squadDocument.getDescription());
        assertThat(result.getMaxCapacity()).isEqualTo(squadDocument.getMaxCapacity());
        assertThat(result.getIsPublic()).isEqualTo(squadDocument.getIsPublic());
    }

    @Test
    void testFromDocumentsWhenValidListOfSquadDocumentsThenReturnListOfSquads() {
        // Arrange
        List<SquadDocument> documents = List.of(squadDocument);

        // Act
        List<Squad> result = Squad.fromDocuments(documents);

        // Assert
        assertThat(result).hasSize(documents.size());
        assertThat(result.get(0).getId()).isEqualTo(documents.get(0).getId());
        assertThat(result.get(0).getName()).isEqualTo(documents.get(0).getName());
        assertThat(result.get(0).getDescription()).isEqualTo(documents.get(0).getDescription());
        assertThat(result.get(0).getMaxCapacity()).isEqualTo(documents.get(0).getMaxCapacity());
        assertThat(result.get(0).getIsPublic()).isEqualTo(documents.get(0).getIsPublic());
    }

    @Test
    void testFromRequestWhenValidSquadRequestThenReturnSquad() {
        // Act
        Squad result = Squad.fromRequest(squadRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(GenId.newUuid(squadRequest.getId()));
        assertThat(result.getName()).isEqualTo(squadRequest.getName());
        assertThat(result.getDescription()).isEqualTo(squadRequest.getDescription());
        assertThat(result.getMaxCapacity()).isEqualTo(squadRequest.getMaxCapacity());
        assertThat(result.getIsPublic()).isEqualTo(squadRequest.getIsPublic());
    }

    @Test
    void testToDocumentWhenValidSquadThenReturnSquadDocument() {
        // Act
        SquadDocument result = squad.toDocument();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(squad.getId());
        assertThat(result.getName()).isEqualTo(squad.getName());
        assertThat(result.getDescription()).isEqualTo(squad.getDescription());
        assertThat(result.getMaxCapacity()).isEqualTo(squad.getMaxCapacity());
        assertThat(result.getIsPublic()).isEqualTo(squad.getIsPublic());
    }
}
