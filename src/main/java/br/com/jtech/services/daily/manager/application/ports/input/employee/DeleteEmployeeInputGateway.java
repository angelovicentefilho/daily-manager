package br.com.jtech.services.daily.manager.application.ports.input.employee;

public interface DeleteEmployeeInputGateway {
    void deleteByEmail(String email);

    void deleteById(String id);

    void deleteByUsername(String username);

    void deleteAll();
}
