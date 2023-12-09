package br.com.jtech.services.daily.manager.application.core.domains.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.squad.SquadRequest;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Member;
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

    public List<Employee> getMembers() {
        return members != null ? members : List.of();
    }
    public static Squad fromDocument(SquadDocument document) {
        var squad = new Squad();
        BeanUtils.copyProperties(document, squad);
        return squad;
    }

    public static Squad fromRequest(SquadRequest request) {
        var squad = new Squad();
        BeanUtils.copyProperties(request, squad);
        squad.setId(GenId.newUuid(request.getId()));
        return squad;
    }

    public SquadDocument toDocument() {
        return SquadDocument.builder().
                id(getId()).
                name(getName()).
                description(getDescription()).
                members(getMembers()).
                maxCapacity(getMaxCapacity()).
                isPublic(getIsPublic()).
                build();
    }
}
