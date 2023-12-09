package br.com.jtech.services.daily.manager.adapters.input.protocols.squad;

import br.com.jtech.services.daily.manager.adapters.input.protocols.employee.EmployeeResponse;
import br.com.jtech.services.daily.manager.application.core.domains.squad.Squad;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SquadResponse implements Serializable {
    private String id;
    private String name;
    private String description;
    private List<EmployeeResponse> members;
    private Integer maxCapacity;
    private Boolean isPublic;

    private List<SquadResponse> squads;

    public static SquadResponse fromDomain(Squad domain) {
        var response = new SquadResponse();
        BeanUtils.copyProperties(domain, response);
        response.setId(domain.getId().toString());
        return response;
    }

    public static SquadResponse fromDomains(List<Squad> squads) {
        var list =  squads.stream().map(SquadResponse::fromDomain).toList();
        return SquadResponse.builder()
                .squads(list)
                .build();
    }
}
