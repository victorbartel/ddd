package org.ddd.samples.repositories;

import org.ddd.samples.data.persistence.repositories.InventoryItemCrud;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemRepository {

    public InventoryItemRepository(InventoryItemCrud inventoryItemCrud) {
    }
}
