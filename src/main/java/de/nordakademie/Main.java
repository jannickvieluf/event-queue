package de.nordakademie;

import de.nordakademie.vieluf.experiment.Experiment;
import de.nordakademie.vieluf.factory.*;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<EventQueueFactory<Object>> eventQueueFactories = Set.of(
                new ArrayEventQueueFactory<>(),
                new ListEventQueueFactory<>(),
                new PriorityQueueEventQueueFactory<>(),
                new UnsortedArrayEventQueueFactory<>()
        );
        Experiment experiment = new Experiment(eventQueueFactories, 100_000, 10_000, 10);
        experiment.testEventQueues();
        // Foo
    }
}
