package de.nordakademie.vieluf;

import de.nordakademie.EventQueue;

public class ArrayEventQueue<E> implements EventQueue<E> {

    private Entry<E>[] array;
    private int entryCount;

    public ArrayEventQueue() {
        this(200);
    }

    public ArrayEventQueue(int initialSize) {
        //noinspection unchecked
        this.array = new Entry[initialSize];
    }

    @Override
    public void enqueue(Double time, E event) {
        int insertionIndex = binaryInsertion(time);
        System.arraycopy(array, insertionIndex, array, insertionIndex + 1, entryCount - insertionIndex);
        array[insertionIndex] = new EntryImpl<>(time, event);

        entryCount++;
        if (array.length - entryCount < 100) {
            changeArraySize(array.length + 100);
        }
    }

    @Override
    public Entry<E> dequeue() {
        Entry<E> entry = new EntryImpl<>(array[0].time(), array[0].event());
        entryCount--;

        System.arraycopy(array, 1, array, 0, entryCount);
        if (array.length - entryCount > 200) {
            changeArraySize(array.length - 100);
        }
        return entry;
    }

    private int binaryInsertion(double time) {
        int low = 0;
        int high = entryCount;
        if (high == low) return low;
        int mid;
        while (high - low > 1) {
            mid = (low + high) / 2;
            if (array[mid].time() < time) {
                low = mid;
            } else if (array[mid].time() > time) {
                high = mid;
            } else {
                return mid;
            }
        }
        return array[low].time() > time ? low : high;
    }

    private void changeArraySize(int newSize) {
        //noinspection unchecked
        Entry<E>[] copy = new Entry[newSize];
        System.arraycopy(array, 0, copy, 0, entryCount);
        array = copy;
    }

    public void printEvents() {
        for (int i = 0; i < entryCount; i++) {
            System.out.println(array[i].time());
        }
    }

    private record EntryImpl<E>(Double time, E event) implements Entry<E> {
    }
}
