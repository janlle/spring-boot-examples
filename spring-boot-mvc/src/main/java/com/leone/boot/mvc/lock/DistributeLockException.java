package com.leone.boot.mvc.lock;

/**
 * 分布式锁异常
 *
 * @author leone
 */
public class DistributeLockException extends RuntimeException {

    public DistributeLockException() {
    }

    public DistributeLockException(String message) {
        super(message);
    }

    public DistributeLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributeLockException(Throwable cause) {
        super(cause);
    }

    public DistributeLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
