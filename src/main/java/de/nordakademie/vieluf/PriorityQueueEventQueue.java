package de.nordakademie.vieluf;

import de.nordakademie.EventQueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueEventQueue<E> implements EventQueue<E> {

    private final Queue<Entry<E>> queue;

    public PriorityQueueEventQueue() {
        this(200);
    }

    public PriorityQueueEventQueue(int initialSize) {
        queue = new PriorityQueue<>(initialSize, new EventComparator());
    }

    @Override
    public void enqueue(Double time, E event) {
        queue.add(new EntryImpl<>(time, event));
    }

    @Override
    public Entry<E> dequeue() {
        return queue.remove();
    }

    public void printEvents() {
        while (queue.iterator().hasNext()) {
            System.out.println(queue.remove().time());
        }
    }

    private record EntryImpl<E>(Double time, E event) implements Entry<E> {
    }

    private class EventComparator implements Comparator<Entry<E>> {

        @Override
        public int compare(Entry e1, Entry e2) {
            if (e1.time() < e2.time()) {
                return -1;
            } else if (e1.time() > e2.time()) {
                return 1;
            }
            return 0;
        }
    }
}
