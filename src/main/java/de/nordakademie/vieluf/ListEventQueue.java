package de.nordakademie.vieluf;

import de.nordakademie.EventQueue;

import java.util.ArrayList;
import java.util.List;

public class ListEventQueue<E> implements EventQueue<E> {

    private final List<Entry<E>> queue;

    public ListEventQueue() {
        this(100);
    }

    public ListEventQueue(int initialSize) {
        queue = new ArrayList<>(initialSize);
    }

    @Override
    public void enqueue(Double time, E event) {
        if (queue.isEmpty()) {
            queue.add(new EntryImpl<>(time, event));
        } else {
            for (int i = 0; i < queue.size(); i++) {
                Double timeStampListItem = queue.get(i).time();
                if (time < timeStampListItem) {
                    queue.add(i, new EntryImpl<>(time, event));
                    return;
                }
            }
            queue.add(new EntryImpl<>(time, event));
        }
    }

    @Override
    public Entry<E> dequeue() {
        return queue.remove(0);
    }

    public void printEvents() {
        for (Entry<E> entry : queue) {
            System.out.println(entry.time());
        }
    }

    private record EntryImpl<E>(Double time, E event) implements Entry<E> {
    }
}
