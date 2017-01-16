package org.ddd.samples.data.persistence.facades.writable.commands;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AssignNewInventoryItem implements Command {
    public abstract long getEmployeeId();
    public abstract String getTitle();
    public abstract int getCount();

    public static AssignNewInventoryItem create(long employeeId, String title, int count) {
        return new AutoValue_AssignNewInventoryItem(employeeId, title, count);
    }

    public static Class<? extends AssignNewInventoryItem> getValueClass() {
        return AutoValue_AssignNewInventoryItem.class;
    }
}
