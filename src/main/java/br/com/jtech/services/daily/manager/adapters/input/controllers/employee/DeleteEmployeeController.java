package br.com.jtech.services.daily.manager.adapters.input.controllers.employee;

import br.com.jtech.services.daily.manager.application.ports.input.employee.DeleteEmployeeInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dailies")
@RequiredArgsConstructor
public class DeleteEmployeeController {

    private final DeleteEmployeeInputGateway deleteEmployeeInputGateway;

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable String email) {
        deleteEmployeeInputGateway.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        deleteEmployeeInputGateway.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        deleteEmployeeInputGateway.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        deleteEmployeeInputGateway.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
