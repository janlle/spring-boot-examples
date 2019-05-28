package com.leone.boot.common.design.behavior.state;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class Client {

    public static void main(String[] args) {
        VoteManager vm = new VoteManager();
        for (int i = 0; i < 9; i++) {
            vm.vote("u1", "A");
        }
    }

}