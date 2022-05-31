package de.nordakademie.vieluf;

import de.nordakademie.EventQueue;

public class UnsortedArrayEventQueue<E> implements EventQueue<E> {

    private Entry<E>[] array;
    private int entryCount;

    public UnsortedArrayEventQueue() {
        this(200);
    }

    public UnsortedArrayEventQueue(int initialSize) {
        //noinspection unchecked
        this.array = new Entry[initialSize];
    }


    @Override
    public void enqueue(Double time, E event) {
        array[entryCount] = new EntryImpl<>(time, event);
        entryCount++;

        if (array.length - entryCount < 100) {
            changeArraySize(array.length + 100);
        }
    }

    @Override
    public Entry<E> dequeue() {
        int smallestIndex = 0;
        for (int i = 1; i < entryCount; i++) {
            if (array[i].time() < array[smallestIndex].time()) {
                smallestIndex = i;
            }
        }

        Entry<E> entry = new EntryImpl<>(array[smallestIndex].time(), array[smallestIndex].event());
        entryCount--;

        if (smallestIndex != entryCount) {
            System.arraycopy(array, smallestIndex + 1, array, smallestIndex, entryCount - smallestIndex);
        }
        if (array.length - entryCount > 200) {
            changeArraySize(array.length - 100);
        }
        return entry;
    }

    public void printEvents() {
        for (int i = 0; i < entryCount; i++) {
            System.out.println(array[i].time());
        }
    }

    private void changeArraySize(int newSize) {
        //noinspection unchecked
        Entry<E>[] copy = new Entry[newSize];
        System.arraycopy(array, 0, copy, 0, entryCount);
        array = copy;
    }

    private record EntryImpl<E>(Double time, E event) implements Entry<E> {
    }
}
