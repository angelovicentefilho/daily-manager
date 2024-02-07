/*
 *  @(#)DailyRepository.java
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
package br.com.jtech.services.daily.manager.adapters.output.repositories;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.daily.DailyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * class DailyRepository
 *
 * @author angelo.vicente@jtech.corp
 */
@Repository
public interface DailyRepository extends MongoRepository<DailyDocument, UUID> {

    List<DailyDocument> findByCreatedAt(LocalDateTime dateTime);

    List<DailyDocument> findBySquad_Name(String squad);

    Optional<DailyDocument> findBySquad_NameAndCreatedAt(String squad, LocalDateTime createdAt);

    Optional<DailyDocument> findTopBySquad_NameOrderByCreateAtDesc(String squadName);

    boolean existsByIdAndFinishedAtIsNull(UUID id);
}