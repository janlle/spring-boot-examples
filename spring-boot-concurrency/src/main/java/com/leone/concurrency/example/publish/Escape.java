package com.leone.concurrency.example.publish;

import com.leone.concurrency.annotations.NotRecommend;
import com.leone.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Leone
 * @since 2018-05-06
 **/
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanEscape = 0;

    public Escape() {
        new InnerClass();
    }

    public static void main(String[] args) {
        new Escape();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}", Escape.this.thisCanEscape);
        }
    }

}
