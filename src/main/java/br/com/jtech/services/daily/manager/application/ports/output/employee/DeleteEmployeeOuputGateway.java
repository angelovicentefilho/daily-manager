package br.com.jtech.services.daily.manager.application.ports.output.employee;

import java.util.UUID;

public interface DeleteEmployeeOuputGateway {
    void deleteById(UUID id);

    void deleteAll();
}
