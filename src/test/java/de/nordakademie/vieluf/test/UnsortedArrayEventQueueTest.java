package de.nordakademie.vieluf.test;

import de.nordakademie.vieluf.factory.UnsortedArrayEventQueueFactory;

public class UnsortedArrayEventQueueTest extends EventQueueTest {

    public UnsortedArrayEventQueueTest() {
        super(new UnsortedArrayEventQueueFactory<>());
    }
}
