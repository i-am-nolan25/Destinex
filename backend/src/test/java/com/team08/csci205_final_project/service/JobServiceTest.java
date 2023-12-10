package com.team08.csci205_final_project.service;

import com.team08.csci205_final_project.event.JobPostedEvent;
import com.team08.csci205_final_project.model.DTO.Job.NewJobRequest;
import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.User.User;
import com.team08.csci205_final_project.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private UserService userService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private JobService jobService;

    @Test
    public void testCreateJob() {
        NewJobRequest newJobRequest = new NewJobRequest();
        // Set properties for newJobRequest

        String currentUserId = "userId";
        User user = new User();
        user.setId(currentUserId);

        when(userService.getCurrentUserId()).thenReturn(currentUserId);
        when(userService.findUserById(currentUserId)).thenReturn(Optional.of(user));

        Job savedJob = new Job();
        when(jobRepository.save(any(Job.class))).thenReturn(savedJob);

        Job result = jobService.createJob(newJobRequest);

        assertNotNull(result);
        verify(jobRepository).save(any(Job.class));
        verify(eventPublisher).publishEvent(any(JobPostedEvent.class));
    }

    @Test
    public void testFindJobById() {
        String jobId = "jobId";
        Optional<Job> mockJob = Optional.of(new Job());
        when(jobRepository.findById(jobId)).thenReturn(mockJob);

        Optional<Job> result = jobService.findJobById(jobId);

        assertTrue(result.isPresent());
        assertEquals(mockJob.get(), result.get());
        verify(jobRepository).findById(jobId);
    }

    @Test
    public void testUpdateJob() {
        Job job = new Job();
        job.setId("jobId");

        when(jobRepository.existsById(job.getId())).thenReturn(true);
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        Job result = jobService.updateJob(job);

        assertNotNull(result);
        verify(jobRepository).save(job);
    }


    @Test
    public void testDeleteJob() {
        String jobId = "jobId";
        when(jobRepository.existsById(jobId)).thenReturn(true);

        boolean result = jobService.deleteJob(jobId);

        assertTrue(result);
        verify(jobRepository).deleteById(jobId);
    }

}
