package br.com.jtech.services.daily.manager.adapters.output.repositories;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.squad.SquadDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SquadRepository extends MongoRepository<SquadDocument, UUID> {
}
