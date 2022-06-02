import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.ListEventQueue;
import de.nordakademie.vieluf.factory.ListEventQueueFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ListEventQueueTest {

    @Test
    public void testListEventQueueFactory() {
        EventQueue<Object> eventQueue = new ListEventQueueFactory<>().createQueue();
        Assertions.assertTrue(eventQueue instanceof ListEventQueue<Object>);
    }

    @Test
    public void testListEventQueue() {
        EventQueue<Object> eventQueue = new ListEventQueueFactory<>().createQueue();
        double[] testData = createTestData();

        double minTimeStamp = Arrays
                .stream(testData)
                .min()
                .orElseThrow(() -> new RuntimeException("No min value on empty stream"));
        double maxTimeStamp = Arrays
                .stream(testData)
                .max()
                .orElseThrow(() -> new RuntimeException("No max value on empty stream"));

        for (double timeStamp : testData) {
            eventQueue.enqueue(timeStamp, new Object());
        }

        for (int i = 0; i < testData.length; i++) {
            if (i == 0) {
                Assertions.assertEquals(minTimeStamp, eventQueue.dequeue().time().doubleValue());
                continue;
            }
            if (i == testData.length - 1) {
                Assertions.assertEquals(maxTimeStamp, eventQueue.dequeue().time().doubleValue());
                continue;
            }
            eventQueue.dequeue();
        }
    }

    private double[] createTestData() {
        double[] data = new double[100];
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        }
        return data;
    }
}
