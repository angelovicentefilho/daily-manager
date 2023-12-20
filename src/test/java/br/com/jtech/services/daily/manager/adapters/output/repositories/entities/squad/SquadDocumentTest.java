package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad;

import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

class SquadDocumentTest {

    private Squad squadEntity;

    @BeforeEach
    void setUp() {
        // Create a Squad entity with dummy data to be used in the tests
        squadEntity = Squad.builder()
                .id(UUID.randomUUID())
                .name("Alpha Team")
                .description("A squad focused on project Alpha.")
                .members(List.of(
                        Employee.builder().id(UUID.randomUUID()).name("John Doe").build(),
                        Employee.builder().id(UUID.randomUUID()).name("Jane Roe").build()
                ))
                .maxCapacity(10)
                .isPublic(true)
                .build();
    }

    @Test
    void testFromDomainWhenValidSquadThenReturnSquadDocument() {
        // Act
        SquadDocument squadDocument = SquadDocument.fromDomain(squadEntity);

        // Assert
        assertThat(squadDocument).isNotNull();
        assertThat(squadDocument.getId()).isEqualTo(squadEntity.getId());
        assertThat(squadDocument.getName()).isEqualTo(squadEntity.getName());
        assertThat(squadDocument.getDescription()).isEqualTo(squadEntity.getDescription());
        assertThat(squadDocument.getMembers()).hasSameSizeAs(squadEntity.getMembers());
        assertThat(squadDocument.getMaxCapacity()).isEqualTo(squadEntity.getMaxCapacity());
        assertThat(squadDocument.getIsPublic()).isEqualTo(squadEntity.getIsPublic());
    }
}
