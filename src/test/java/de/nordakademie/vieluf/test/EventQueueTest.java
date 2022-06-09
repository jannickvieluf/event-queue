package de.nordakademie.vieluf.test;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.factory.EventQueueFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class EventQueueTest {

    private final EventQueue<Object> eventQueue;

    public EventQueueTest(EventQueueFactory<Object> eventQueueFactory) {
        this.eventQueue = eventQueueFactory.createQueue();
    }

    @Test
    public void testStaticArrayEventQueue() {
        double[] testData = createTestData(100);
        double currentTimeStamp = Arrays
                .stream(testData)
                .min()
                .orElseThrow(() -> new RuntimeException("Empty test data"));

        for (double timeStamp : testData) {
            eventQueue.enqueue(timeStamp, new Object());
        }

        while (eventQueue.hasNext()) {
            Assertions.assertTrue(eventQueue.dequeue().time() >= currentTimeStamp);
        }
    }

    @Test
    public void testDynamicArrayEventQueue() {
        double[] baseData = createTestData(100);
        double currentTimeStamp = Arrays
                .stream(baseData)
                .min()
                .orElseThrow(() -> new RuntimeException("Empty test data"));
        double[] testData = createTestData(50);

        for (double timeStamp : baseData) {
            eventQueue.enqueue(timeStamp, new Object());
        }

        int i = 0;
        while (eventQueue.hasNext()) {
            Assertions.assertTrue(eventQueue.dequeue().time() >= currentTimeStamp);
            if (i < testData.length) {
                if (testData[i] < currentTimeStamp) {
                    currentTimeStamp = testData[i];
                }
                eventQueue.enqueue(testData[i], new Object());
                i++;
            }
        }
    }

    private double[] createTestData(int size) {
        double[] data = new double[size];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        }
        return data;
    }
}
