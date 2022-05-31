package de.nordakademie.vieluf.factory;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.ArrayEventQueue;

public class ArrayEventQueueFactory<E> implements EventQueueFactory<E> {

    @Override
    public EventQueue<E> createQueue() {
        return new ArrayEventQueue<>();
    }

    @Override
    public EventQueue<E> createQueue(int initialSize) {
        return new ArrayEventQueue<>(initialSize);
    }
}
