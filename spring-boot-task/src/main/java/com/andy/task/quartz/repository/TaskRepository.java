package com.andy.task.quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 *
 * @author Leone
 * @since 2018-09-06
 **/
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
