package org.ddd.samples.repositories;

import com.google.common.collect.ImmutableList;
import org.ddd.samples.data.persistence.entities.EmployeeEntity;
import org.ddd.samples.data.persistence.repositories.EmployeeCrud;
import org.ddd.samples.repositories.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.StreamSupport.stream;


@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeCrud employeeCrud;
    private final ModelMapperSupplier modelMapperSupplier;

    public EmployeeRepositoryImpl(EmployeeCrud employeeCrud, ModelMapperSupplier modelMapperSupplier) {
        this.employeeCrud = checkNotNull(employeeCrud);
        this.modelMapperSupplier = checkNotNull(modelMapperSupplier);
    }

    @Override
    public EmployeeDto findOne(Long id) {
        final EmployeeEntity employeeEntity = employeeCrud.findOne(id);
        return toEmployeeDto(employeeEntity);
    }

    @Override
    public List<EmployeeDto> findAll() {
        final Iterable<EmployeeEntity> employees = employeeCrud.findAll();
        return stream(employees.spliterator(), false)
            .map(this::toEmployeeDto)
            .collect(Collectors.toList());
    }

    public EmployeeDto create(String firstName, String lastName) {
        final EmployeeEntity employeeEntity = new EmployeeEntity(firstName, lastName, ImmutableList.of());
        employeeCrud.save(employeeEntity);
        return toEmployeeDto(employeeEntity);
    }

    public void delete(long id) {
        employeeCrud.delete(id);
    }

    private EmployeeDto toEmployeeDto(EmployeeEntity employeeEntity) {
        return modelMapperSupplier.supply().map(employeeEntity, EmployeeDto.class);
    }
}
