package de.nordakademie.vieluf.experiment;

import de.nordakademie.EventQueue;
import de.nordakademie.vieluf.factory.EventQueueFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Experiment {

    private final ThreadMXBean threadTimer;
    private final ThreadLocalRandom random;
    private final Set<EventQueueFactory<Object>> eventQueueFactories;
    private final Map<Double, Object> initialDataSet;
    private final double[] testDataTimes;
    private final Object[] testDataEvents;
    private final int initialSize;
    private final int tests;
    private final int measurements;

    public Experiment(Set<EventQueueFactory<Object>> eventQueueFactories, int initialSize, int tests,
                      int measurements) {
        this.threadTimer = ManagementFactory.getThreadMXBean();
        this.random = ThreadLocalRandom.current();
        this.eventQueueFactories = eventQueueFactories;
        this.initialDataSet = new HashMap<>(initialSize);
        this.testDataTimes = new double[tests];
        this.testDataEvents = new Object[tests];
        createTestData();
        this.initialSize = initialSize;
        this.tests = tests;
        this.measurements = measurements;

        for (int i = 0; i < initialSize; i++) {
            initialDataSet.put(random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE), new Object());
        }
    }

    public void testEventQueues() {
        for (EventQueueFactory<Object> eventQueueFactory : eventQueueFactories) {
            System.out.println("--------------------");
            EventQueue<Object> eventQueue = eventQueueFactory.createQueue(initialSize);
            String eventQueueName = eventQueue.getClass().getSimpleName();
            List<Double> timeMeasurements = new ArrayList<>();

            long initialStartTime = threadTimer.getCurrentThreadCpuTime();
            for (Map.Entry<Double, Object> entry : initialDataSet.entrySet()) {
                eventQueue.enqueue(entry.getKey(), entry.getValue());
            }
            long initialStopTime = threadTimer.getCurrentThreadCpuTime();

            long measurementStartTime = threadTimer.getCurrentThreadCpuTime();
            for (int i = 1; i <= tests; i++) {
                eventQueue.enqueue(testDataTimes[i - 1], testDataEvents[i - 1]);
                eventQueue.dequeue();
                if (i % (tests / measurements) == 0) {
                    long measurementStopTime = threadTimer.getCurrentThreadCpuTime();
                    timeMeasurements.add((measurementStopTime - measurementStartTime) / (double) (tests / measurements));
                    measurementStartTime = threadTimer.getCurrentThreadCpuTime();
                }
            }
            System.out.println(eventQueueName + ":");
            System.out.println("Initial: " + ((initialStopTime - initialStartTime) / 1000000D) + "ms");
            for (int i = 0; i < timeMeasurements.size(); i++) {
                System.out.println("Measurement " + (i + 1) + ": " + timeMeasurements.get(i) + "ns");
            }
            System.out.println("--------------------");
        }
    }

    private void createTestData() {
        for (int i = 0; i < tests; i++) {
            testDataTimes[i] = random.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
            testDataEvents[i] = new Object();
        }
    }
}