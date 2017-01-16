package org.ddd.samples.data.persistence.facades.writable.commands;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ResignEmployee implements Command {

    public abstract long getId();

    public static ResignEmployee create(long id) {
        return new AutoValue_ResignEmployee(id);
    }

}
