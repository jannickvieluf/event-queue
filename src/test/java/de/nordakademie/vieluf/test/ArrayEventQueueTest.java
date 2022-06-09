package de.nordakademie.vieluf.test;

import de.nordakademie.vieluf.factory.ArrayEventQueueFactory;

public class ArrayEventQueueTest extends EventQueueTest {

    public ArrayEventQueueTest() {
        super(new ArrayEventQueueFactory<>());
    }
}
