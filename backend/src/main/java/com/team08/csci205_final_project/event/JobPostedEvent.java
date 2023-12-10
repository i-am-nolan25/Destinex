package com.team08.csci205_final_project.event;

import com.team08.csci205_final_project.model.Job.Job;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class JobPostedEvent extends ApplicationEvent {
    private final Job job;

    public JobPostedEvent(Object source, Job job) {
        super(source);
        this.job = job;
    }

    public Job getJob() {
        return job;
    }
}
