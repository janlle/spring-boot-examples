package com.leone.concurrency.thread.consumer;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class TicketService {

    private int ticketCount = 999;

    /**
     * 消费
     */
    public synchronized void consumption() {
        if (ticketCount < 1) {
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        System.out.println(Thread.currentThread().getName() + " 正在出售第 " + String.format("%03d", ticketCount) + " 张票！");
        ticketCount--;
    }


    /**
     * 消费
     */
    public synchronized void production() {

    }

}
