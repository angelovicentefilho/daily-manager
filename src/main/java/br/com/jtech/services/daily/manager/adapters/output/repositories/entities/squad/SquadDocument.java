package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.BaseDocument;
import br.com.jtech.services.daily.manager.application.core.domains.employee.Employee;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import br.com.jtech.services.daily.manager.config.infra.mongo.Cascade;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias(value = "Squad")
@Document(collection = "squads")
@EqualsAndHashCode(callSuper = true)
public class SquadDocument extends BaseDocument implements Serializable {
    private String name;
    private String description;
    @DBRef
    @Singular
    @Cascade
    private List<Employee> members;
    private Integer maxCapacity;
    private Boolean isPublic;

    public static SquadDocument fromDomain(Squad squad) {
        return null;
    }
}
