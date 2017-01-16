package org.ddd.samples.domain.services;

import org.ddd.samples.domain.aggregates.Employee;

public interface EmployeeService {
    Employee createBrandNew();

    Employee findExisting(long id);
}
