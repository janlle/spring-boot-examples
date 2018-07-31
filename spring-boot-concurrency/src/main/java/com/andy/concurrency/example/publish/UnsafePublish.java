package com.andy.concurrency.example.publish;

import com.andy.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: lyon
 * @since: 2018-05-06 13:06
 **/
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String [] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";

        log.info("{}", Arrays.toString(unsafePublish.getStates()));

    }



}
