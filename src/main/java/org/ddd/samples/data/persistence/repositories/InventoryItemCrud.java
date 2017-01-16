package org.ddd.samples.data.persistence.repositories;

import org.ddd.samples.data.persistence.entities.InventoryItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface InventoryItemCrud extends CrudRepository<InventoryItemEntity, Long> {

}
