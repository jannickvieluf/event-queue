import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.factory.ArrayEventQueueFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayEventQueueTest {

    @Test
    public void testArrayEventQueueFactory() {
        Assertions.assertNotNull(new ArrayEventQueueFactory<>().createQueue());
    }

    @Test
    public void testArrayEventQueue() {
        EventQueue<Object> eventQueue = new ArrayEventQueueFactory<>().createQueue();
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
