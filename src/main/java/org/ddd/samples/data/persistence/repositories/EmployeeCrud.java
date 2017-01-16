package org.ddd.samples.data.persistence.repositories;

import org.ddd.samples.data.persistence.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeCrud extends CrudRepository<EmployeeEntity, Long> {
}
