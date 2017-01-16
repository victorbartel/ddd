package org.ddd.samples.domain.aggregates;

import org.ddd.samples.data.persistence.facades.writable.commands.Command;
import org.ddd.samples.events.EventBusDelegateImpl;

public interface AggregateRoot {
    default void applyChange(Command event) {
        EventBusDelegateImpl.INSTANCE.post(event);
    }
}
