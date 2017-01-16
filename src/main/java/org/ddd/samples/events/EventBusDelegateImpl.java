package org.ddd.samples.events;

import com.google.common.eventbus.EventBus;

public class EventBusDelegateImpl implements EventBusDelegate {

    public static final EventBusDelegate INSTANCE = new EventBusDelegateImpl();
    private final EventBus delegate;

    private EventBusDelegateImpl() {
        delegate = new EventBus();
    }

    @Override
    public void register(Object object) {
        delegate.register(object);
    }

    @Override
    public void unregister(Object object) {
        delegate.unregister(object);
    }

    @Override
    public void post(Object event) {
        delegate.post(event);
    }
}
