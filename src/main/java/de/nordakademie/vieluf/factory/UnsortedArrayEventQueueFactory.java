package de.nordakademie.vieluf.factory;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.UnsortedArrayEventQueue;

public class UnsortedArrayEventQueueFactory<E> implements EventQueueFactory<E> {

    @Override
    public EventQueue<E> createQueue() {
        return new UnsortedArrayEventQueue<>();
    }

    @Override
    public EventQueue<E> createQueue(int initialSize) {
        return new UnsortedArrayEventQueue<>(initialSize);
    }
}
