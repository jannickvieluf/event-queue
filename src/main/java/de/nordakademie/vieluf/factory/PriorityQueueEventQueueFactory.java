package de.nordakademie.vieluf.factory;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.PriorityQueueEventQueue;

public class PriorityQueueEventQueueFactory<E> implements EventQueueFactory<E> {

    @Override
    public EventQueue<E> createQueue() {
        return new PriorityQueueEventQueue<>();
    }

    @Override
    public EventQueue<E> createQueue(int initialSize) {
        return new PriorityQueueEventQueue<>(initialSize);
    }
}
