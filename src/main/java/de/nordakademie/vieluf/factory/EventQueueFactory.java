package de.nordakademie.vieluf.factory;

import de.nordakademie.EventQueue;

public interface EventQueueFactory<E> {

    EventQueue<E> createQueue();

    EventQueue<E> createQueue(int initialSize);
}
