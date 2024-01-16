package br.com.jtech.services.daily.manager.adapters.output.repositories;

import br.com.jtech.services.daily.manager.adapters.output.repositories.entities.employee.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeDocument, UUID> {
    
    Optional<EmployeeDocument> findByEmail(String email);

    Optional<EmployeeDocument> findByUsername(String username);
}
