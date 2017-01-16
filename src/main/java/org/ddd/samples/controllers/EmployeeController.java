package org.ddd.samples.controllers;

import org.ddd.samples.domain.aggregates.Employee;
import org.ddd.samples.domain.services.EmployeeService;
import org.ddd.samples.repositories.EmployeeRepository;
import org.ddd.samples.repositories.dto.EmployeeDto;
import org.ddd.samples.viewmodels.EmployeeViewModel;
import org.ddd.samples.viewmodels.InventoryItemViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RequestMapping("api/v1/employees")
@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public List<EmployeeDto> index() {
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/hire", method = POST)
    public ResponseEntity<Void> hire(@RequestBody EmployeeViewModel employeeViewModel) {
        if (!employeeViewModel.isValid()) {
            return status(BAD_REQUEST).body(null);
        }
        employeeService.createBrandNew().hire(employeeViewModel);

        return status(CREATED).body(null);
    }

    @RequestMapping(value = "{employeeId}/assign-new-inventory-item", method = POST)
    public ResponseEntity<Void> assignNewInventoryItem(@PathVariable long employeeId,
                                                       @RequestBody InventoryItemViewModel inventoryItemViewModel) {
        if (!inventoryItemViewModel.isValid()) {
            return status(BAD_REQUEST).body(null);
        }

        employeeService.findExisting(employeeId).assignNewInventoryItem(inventoryItemViewModel);

        return status(CREATED).body(null);
    }

}
