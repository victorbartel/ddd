package org.ddd.samples.repositories.dto;


import com.google.auto.value.AutoValue;

@AutoValue
public abstract class InventoryItemDto {
    public abstract Long getId();
    public abstract String getTitle();
    public abstract int getQuantity();

    public static InventoryItemDto create(Long id, String title, int quantity) {
        return new AutoValue_InventoryItemDto(id, title, quantity);
    }
}
