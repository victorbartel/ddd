package org.ddd.samples.domain.aggregates;

import com.google.common.collect.ImmutableList;
import org.ddd.samples.data.persistence.facades.writable.commands.AssignNewInventoryItem;
import org.ddd.samples.data.persistence.facades.writable.commands.HireEmployee;
import org.ddd.samples.repositories.EmployeeRepository;
import org.ddd.samples.repositories.dto.EmployeeDto;
import org.ddd.samples.viewmodels.EmployeeViewModel;
import org.ddd.samples.viewmodels.InventoryItemViewModel;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Employee implements AggregateRoot {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDto employeeDto;
    private EmployeeStatus status = EmployeeStatus.UNKNOWN;

    private Employee(EmployeeRepository employeeRepository) {
        this.employeeDto = null;
        this.employeeRepository = checkNotNull(employeeRepository);
    }

    private Employee(EmployeeDto employeeDto, EmployeeRepository employeeRepository)  {
        this.employeeDto        = checkNotNull(employeeDto);
        this.employeeRepository = checkNotNull(employeeRepository);
        this.status             = EmployeeStatus.HIRED;
    }

    public Employee hire(EmployeeViewModel model) {
        if (status != EmployeeStatus.UNKNOWN && status != EmployeeStatus.RESIGNED) {
            throw new IllegalStateException("already hired");
        }
        this.applyChange(HireEmployee.create(model.getFirstName(), model.getLastName()));

        final EmployeeDto employeeDto = EmployeeDto.create(Long.MIN_VALUE, model.getFirstName(), model.getLastName(), ImmutableList.of());
        final Employee result         = new Employee(employeeDto, employeeRepository);

        result.status = EmployeeStatus.WAITING;

        return result;
    }

    public Employee assignNewInventoryItem(InventoryItemViewModel item) {
        if (status != EmployeeStatus.HIRED) {
            throw new IllegalStateException("Inventory item can be assigned to an active employee");
        }
        this.applyChange(AssignNewInventoryItem.create(employeeDto.getId(), item.getTitle(), item.getCount()));
        return this;
    }

    public static Employee createBrandNew(EmployeeRepository employeeRepository) {
        return new Employee(employeeRepository);
    }

    public static Employee createExisting(long id, EmployeeRepository employeeRepository) {
        final EmployeeDto employeeDto = employeeRepository.findOne(id);
        if (employeeDto == null) throw new IllegalStateException("Employee not found for getId "+id);

        return new Employee(employeeDto, employeeRepository);
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    private enum EmployeeStatus {
        UNKNOWN,
        WAITING,
        HIRED,
        RESIGNED
        ;
    }

}
