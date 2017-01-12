package org.hacklist.util.misc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Neil Alishev
 */
public class CountdownLatchSingleton {
    private static CountDownLatch countDownLatch;

    public static void init() {
        countDownLatch = new CountDownLatch(1);
    }

    public static CountDownLatch getCountDownLatch() {
        if(countDownLatch == null) {
            throw new IllegalStateException("You should call init() method first!");
        }

        return countDownLatch;
    }
}
