/*
 *  @(#)DailyDocument.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.BaseDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import br.com.jtech.services.daily.manager.config.infra.mongo.Cascade;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * class DailyDocument
 *
 * @author angelo.vicente@jtech.corp
 */
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TypeAlias(value = "Daily")
@Document(collection = "dailies")
@EqualsAndHashCode(callSuper = true)
public class DailyDocument extends BaseDocument implements Serializable {

    private String summary;

    @DBRef
    @Cascade
    private SquadDocument squad;
    @DBRef
    @Cascade
    private EmployeeDocument author;
    private LocalDateTime createAt;
    @DBRef
    @Cascade
    @Singular
    private List<TaskDocument> tasks;
    @DBRef
    @Cascade
    @Singular
    private List<BlockerDocument> blockers;
}