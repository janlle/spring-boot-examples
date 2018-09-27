package com.andy.task.quartz.config;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.andy.task.quartz.jobs.TaskA;
import org.quartz.Trigger;
>>>>>>> c0d388505f53ae1a871fc8506c0b32e9978e4a27
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> c0d388505f53ae1a871fc8506c0b32e9978e4a27

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-15
 **/
@Configuration
@EnableScheduling
public class QuartzConfig {

<<<<<<< HEAD
    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        // Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setStartupDelay(20);
        // 设置自定义Job Factory，用于Spring管理Job bean
        factory.setJobFactory(jobFactory);
        return factory;
=======
    /**
     * 配置定时任务
     * ScheduleTask为需要执行的任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(TaskA task) {
        MethodInvokingJobDetailFactoryBean detailFactory = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        detailFactory.setConcurrent(false);
        // 设置任务的名字
        detailFactory.setName("jobName-a");
        // 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        detailFactory.setGroup("jobGroup-a");
        //为需要执行的实体类对应的对象
        detailFactory.setTargetObject(task);
        //添加需要执行的方法
        //通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的需要执行方法
        detailFactory.setTargetMethod("execute");
        return detailFactory;
    }

    /**
     * 配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean detailFactoryBean) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(Objects.requireNonNull(detailFactoryBean.getObject()));
        //初始时的cron表达式  每5分钟执行一次
        trigger.setCronExpression("* 0/5 * * * ?");
        //trigger的name
        trigger.setName("executeTask");
        return trigger;

>>>>>>> c0d388505f53ae1a871fc8506c0b32e9978e4a27
    }

}