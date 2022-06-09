package de.nordakademie.vieluf.test;

import de.nordakademie.vieluf.factory.ListEventQueueFactory;

public class ListEventQueueTest extends EventQueueTest {

    public ListEventQueueTest() {
        super(new ListEventQueueFactory<>());
    }
}
