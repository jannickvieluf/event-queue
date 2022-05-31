package de.nordakademie.vieluf.factory;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.ListEventQueue;

public class ListEventQueueFactory<E> implements EventQueueFactory<E> {

    @Override
    public EventQueue<E> createQueue() {
        return new ListEventQueue<>();
    }

    @Override
    public EventQueue<E> createQueue(int initialSize) {
        return new ListEventQueue<>(initialSize);
    }
}
