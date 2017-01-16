package org.ddd.samples.data.persistence.entities;

import javax.persistence.*;

@Entity
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Basic(optional=false)
    private String title;
    @Basic(optional=false)
    private int quantity;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name="OWNER_ID")
    private EmployeeEntity owner;

    private InventoryItemEntity() {
    }

    public InventoryItemEntity(String title, int count, EmployeeEntity owner) {
        this.title = title;
        this.quantity = count;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EmployeeEntity getOwner() {
        return owner;
    }

    public void setOwner(EmployeeEntity owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
            "getId=" + id +
            ", getTitle='" + title + '\'' +
            ", getQuantity=" + quantity +
            '}';
    }
}
