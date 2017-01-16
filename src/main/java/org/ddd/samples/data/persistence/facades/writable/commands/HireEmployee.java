package org.ddd.samples.data.persistence.facades.writable.commands;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class HireEmployee implements Command {

    public abstract String getFirstName();
    public abstract String getLastName();

    public static HireEmployee create(String firstName, String lastName) {
        return new AutoValue_HireEmployee(firstName, lastName);
    }

    public static Class<? extends HireEmployee> getValueClass() {
        return AutoValue_HireEmployee.class;
    }

}
