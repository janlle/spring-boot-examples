package com.leone.boot.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * <p>监听器实现JobExecutionListener接口，并重写其beforeJob、afterJob方法即可
 *
 * @author leone
 * @since 2018-10-08
 **/
public class CsvJobListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(CsvJobListener.class);

    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        log.info("任务处理开始...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long endTime = System.currentTimeMillis();
        log.info("任务处理结束，耗时:{} ms", (endTime - startTime));
    }
}
