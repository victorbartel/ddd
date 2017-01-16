package org.ddd.samples.data.persistence.entities;

import com.google.common.collect.ImmutableList;

import javax.persistence.*;
import java.util.List;

@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Basic(optional=false)
    private String firstName;
    @Basic(optional=false)
    private String lastName;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="owner", cascade = CascadeType.DETACH)
    private List<InventoryItemEntity> inventoryItemEntities;

    private EmployeeEntity() {
    }

    public EmployeeEntity(String firstName, String lastName, List<InventoryItemEntity> inventoryItemEntities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.inventoryItemEntities = inventoryItemEntities;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "getId=" + id +
            ", getFirstName='" + firstName + '\'' +
            ", getLastName='" + lastName + '\'' +
            ", getInventoryItems=" + inventoryItemEntities +
            '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<InventoryItemEntity> getInventoryItemEntities() {
        return inventoryItemEntities;
    }

    public void setInventoryItem(List<InventoryItemEntity> inventoryItemEntities) {
        this.inventoryItemEntities = inventoryItemEntities;
    }

    public Long getId() {
        return id;
    }
}
