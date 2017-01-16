package org.ddd.samples.domain.services;

import org.ddd.samples.domain.aggregates.Employee;
import org.ddd.samples.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = checkNotNull(employeeRepository);
    }

    @Override
    public Employee createBrandNew() {
        return Employee.createBrandNew(employeeRepository);
    }

    @Override
    public Employee findExisting(long id) {
        return Employee.createExisting(id, employeeRepository);
    }
}
