package br.com.jtech.services.daily.manager.adapters.output.employee;

import br.com.jtech.services.daily.manager.adapters.output.repositories.EmployeeRepository;
import br.com.jtech.services.daily.manager.application.ports.output.employee.DeleteEmployeeOuputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteEmployeeAdapter implements DeleteEmployeeOuputGateway {

    private final EmployeeRepository repository;

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

}
