package org.ddd.samples.data.persistence.facades.writable.handlers;


import com.google.common.eventbus.Subscribe;
import org.ddd.samples.data.persistence.entities.EmployeeEntity;
import org.ddd.samples.data.persistence.entities.InventoryItemEntity;
import org.ddd.samples.data.persistence.facades.writable.commands.AssignNewInventoryItem;
import org.ddd.samples.data.persistence.facades.writable.commands.HireEmployee;
import org.ddd.samples.data.persistence.facades.writable.commands.ResignEmployee;
import org.ddd.samples.data.persistence.repositories.EmployeeCrud;
import org.ddd.samples.data.persistence.repositories.InventoryItemCrud;
import org.ddd.samples.repositories.ModelMapperSupplier;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class EmployeeHandlerImpl implements EmployeeHandler {
    private final EmployeeCrud employeeCrudRepository;
    private final InventoryItemCrud inventoryItemCrudRepository;
    private final ModelMapperSupplier modelMapperSupplier;

    public EmployeeHandlerImpl(EmployeeCrud employeeCrudRepository,
                               InventoryItemCrud inventoryItemCrudRepository,
                               ModelMapperSupplier modelMapperSupplier) {
        this.employeeCrudRepository = checkNotNull(employeeCrudRepository);
        this.inventoryItemCrudRepository = checkNotNull(inventoryItemCrudRepository);
        this.modelMapperSupplier = checkNotNull(modelMapperSupplier);
    }

    @Override
    @Subscribe
    public void handle(HireEmployee message) {
        final EmployeeEntity employeeEntity = getMapper().map(message, EmployeeEntity.class);

        employeeCrudRepository.save(employeeEntity);
    }

    @Override
    @Subscribe
    public void handle(AssignNewInventoryItem message) {
        final EmployeeEntity employeeEntity = employeeCrudRepository.findOne(message.getEmployeeId());
        final InventoryItemEntity inventoryItemEntity = getMapper().map(message, InventoryItemEntity.class);

        inventoryItemEntity.setOwner(employeeEntity);
        employeeEntity.getInventoryItemEntities().add(inventoryItemEntity);

        employeeCrudRepository.save(employeeEntity);
        inventoryItemCrudRepository.save(inventoryItemEntity);
    }

    @Override
    @Subscribe
    public void handle(ResignEmployee message) {
        employeeCrudRepository.delete(message.getId());
    }

    private ModelMapper getMapper() {
        return modelMapperSupplier.supply();
    }
}
