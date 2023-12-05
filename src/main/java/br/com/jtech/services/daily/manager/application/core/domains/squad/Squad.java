package br.com.jtech.services.daily.manager.application.core.domains.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Squad {
    private UUID id;
    private String name;
    private String description;
    private List<Employee> members;
    private Integer maxCapacity;
    private Boolean isPublic;

    public static Squad fromDocument(SquadDocument squad) {
        return null;
    }

    public static Squad fromRequest(SquadRequest squad) {
        return null;
    }
}
