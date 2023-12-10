/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Fall 2023
 * Instructor: Prof. Brian King / Prof. Joshua Stough
 *
 * Name: Hung Ngo
 * Section: YOUR SECTION
 * Date: 16/11/2023
 * Time: 15:38
 *
 * Project: csci205_final_project
 * Package: com.team08.csci205_final_project.config
 * Class: AsyncConfig
 *
 * Description:
 *
 * ****************************************
 */
package com.team08.csci205_final_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/** Custom a multithreading executor */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    @Bean(name = "jobDispatchExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // Set the core number of threads
        executor.setCorePoolSize(5);
        // Set the maximum allowed number of threads
        executor.setMaxPoolSize(10);
        // Set the capacity of the task queue
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("job-dispatch-executor-");
        executor.initialize();
        return executor;
    }
}
