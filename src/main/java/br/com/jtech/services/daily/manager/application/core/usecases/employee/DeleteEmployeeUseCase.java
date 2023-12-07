package br.com.jtech.services.daily.manager.application.core.usecases.employee;

import br.com.jtech.services.daily.manager.application.ports.input.employee.DeleteEmployeeInputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.DeleteEmployeeOuputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByEmailOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByIdOutputGateway;
import br.com.jtech.services.daily.manager.application.ports.output.employee.FindEmployeeByUsernameOutputGateway;
import br.com.jtech.services.daily.manager.config.infra.utils.GenId;

import static br.com.jtech.services.daily.manager.application.core.validators.EmployeeValidator.validateId;

public class DeleteEmployeeUseCase implements DeleteEmployeeInputGateway {

    private final DeleteEmployeeOuputGateway deleteEmployeeOuputGateway;
    private final FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway;
    private final FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway;
    private final FindEmployeeByIdOutputGateway findEmployeeByIdOutputGateway;

    public DeleteEmployeeUseCase(DeleteEmployeeOuputGateway deleteEmployeeOuputGateway,
                                 FindEmployeeByEmailOutputGateway findEmployeeByEmailOutputGateway,
                                 FindEmployeeByUsernameOutputGateway findEmployeeByUsernameOutputGateway,
                                 FindEmployeeByIdOutputGateway findEmployeeByIdOutputGateway) {
        this.deleteEmployeeOuputGateway = deleteEmployeeOuputGateway;
        this.findEmployeeByEmailOutputGateway = findEmployeeByEmailOutputGateway;
        this.findEmployeeByUsernameOutputGateway = findEmployeeByUsernameOutputGateway;
        this.findEmployeeByIdOutputGateway = findEmployeeByIdOutputGateway;
    }


    @Override
    public void deleteByEmail(String email) {
        var employee = findEmployeeByEmailOutputGateway.findByEmail(email);
        deleteEmployeeOuputGateway.deleteById(employee.getId());
    }

    @Override
    public void deleteById(String id) {
        validateId(id);
        var employee = findEmployeeByIdOutputGateway.findById(GenId.newUuid(id));
        deleteEmployeeOuputGateway.deleteById(employee.getId());
    }

    @Override
    public void deleteByUsername(String username) {
        var employee = findEmployeeByUsernameOutputGateway.findByUsername(username);
        deleteEmployeeOuputGateway.deleteById(employee.getId());
    }

    @Override
    public void deleteAll() {
        deleteEmployeeOuputGateway.deleteAll();
    }
}
