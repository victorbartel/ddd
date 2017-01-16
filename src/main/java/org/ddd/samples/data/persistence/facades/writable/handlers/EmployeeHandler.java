package org.ddd.samples.data.persistence.facades.writable.handlers;

import com.google.common.eventbus.Subscribe;
import org.ddd.samples.data.persistence.facades.writable.commands.AssignNewInventoryItem;
import org.ddd.samples.data.persistence.facades.writable.commands.ResignEmployee;
import org.ddd.samples.data.persistence.facades.writable.commands.HireEmployee;

public interface EmployeeHandler {
    void handle(HireEmployee message);

    @Subscribe
    void handle(AssignNewInventoryItem message);

    void handle(ResignEmployee message);
}
