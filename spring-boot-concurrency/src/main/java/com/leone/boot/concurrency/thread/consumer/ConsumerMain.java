package com.leone.boot.concurrency.thread.consumer;

/**
 * <p>
 *
 * @author leone
 * @since 2019-02-11
 **/
public class ConsumerMain {

    public static void main(String[] args) {

        TicketService ticketService = new TicketService();

        new Thread(() -> {
            while (true) {
                ticketService.consumption();
            }
        }, "thread-a").start();

        new Thread(() -> {
            while (true) {
                ticketService.consumption();
            }
        }, "thread-b").start();


        new Thread(() -> {
            while (true) {
                ticketService.consumption();
            }
        }, "thread-c").start();

    }

}
