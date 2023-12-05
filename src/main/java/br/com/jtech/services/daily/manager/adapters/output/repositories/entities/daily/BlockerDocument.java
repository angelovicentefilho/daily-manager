package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.BaseDocument;
import br.com.jtech.services.daily.manager.application.core.domains.daily.Blocker;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias(value = "Blocker")
@Document(collection = "blockers")
@EqualsAndHashCode(callSuper = true)
public class BlockerDocument extends BaseDocument implements Serializable {
    private String descriptor;
    private String resolution;
    private String impact;
    private String owner;
    private String status;
    private LocalDateTime timeEstimate;
    @Singular
    private List<String> labels;
    @Singular
    private List<String> links;

    public static List<BlockerDocument> fromDomains(List<Blocker> blockers) {
        return null;
    }
}
