package org.ddd.samples.events;

public interface EventBusDelegate {
    void register(Object object);

    void unregister(Object object);

    void post(Object event);
}
