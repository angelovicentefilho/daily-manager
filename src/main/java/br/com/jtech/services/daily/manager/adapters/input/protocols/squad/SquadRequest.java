package br.com.jtech.services.daily.manager.adapters.input.protocols.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SquadRequest implements Serializable {
    private String id;
    private String name;
    private String description;
    private List<EmployeeRequest> members;
    private Integer maxCapacity;
    private Boolean isPublic;

    private List<SquadRequest> squads;
}
