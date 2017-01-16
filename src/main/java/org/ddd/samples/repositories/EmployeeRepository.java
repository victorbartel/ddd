package org.ddd.samples.repositories;

import org.ddd.samples.repositories.dto.EmployeeDto;

import java.util.List;

public interface EmployeeRepository {
    EmployeeDto findOne(Long id);

    List<EmployeeDto> findAll();
}
