package de.nordakademie;

public interface EventQueue<E> {

    void enqueue(Double time, E event);

    EventQueue.Entry<E> dequeue();

    boolean hasNext();

    interface Entry<E> {

        Double time();

        E event();
    }
}