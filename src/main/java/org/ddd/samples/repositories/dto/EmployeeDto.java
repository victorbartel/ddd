package org.ddd.samples.repositories.dto;


import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

import java.util.List;

@AutoValue
public abstract class EmployeeDto {
    public abstract Long getId();
    public abstract String getFirstName();
    public abstract String getLastName();
    public abstract List<InventoryItemDto> getInventoryItems();

    public static EmployeeDto create(Long id, String firstName, String lastName, List<InventoryItemDto> inventoryItems) {
        final List<InventoryItemDto> items = inventoryItems == null ? ImmutableList.of() : inventoryItems;
        return new AutoValue_EmployeeDto(id, firstName, lastName, items);
    }
}
