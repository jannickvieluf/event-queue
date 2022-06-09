package de.nordakademie.vieluf.test;

import de.nordakademie.vieluf.factory.PriorityQueueEventQueueFactory;

public class PriorityQueueEventQueueTest extends EventQueueTest {

    public PriorityQueueEventQueueTest() {
        super(new PriorityQueueEventQueueFactory<>());
    }
}
