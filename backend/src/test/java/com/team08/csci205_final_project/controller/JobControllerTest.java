package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.model.DTO.Job.JobInfo;
import com.team08.csci205_final_project.model.DTO.Job.NewJobRequest;
import com.team08.csci205_final_project.model.Job.Job;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team08.csci205_final_project.model.Job.JobStatus;
import com.team08.csci205_final_project.service.JobService;

import org.hamcrest.core.StringContains;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit Test for JobController
 * The test ensures the controller calls
 * the correct services and responses the correct HTTP responses
 */
@WebMvcTest(value = JobController.class)
@ExtendWith(SpringExtension.class)
public class JobControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private JobController jobController;

    @MockBean
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;

    private Job randomJob, randomJob1;

    private String jobRequest;

    private JSONObject jsonObject;

    @BeforeEach
    public void setup(WebApplicationContext wac) throws JSONException {
        objectMapper = new ObjectMapper();

        jsonObject = new JSONObject()
                .put("category", "Food")
                .put("description", "Deliver food to my cat")
                .put("receiverName", "John Doe")
                .put("receiverAddress", "123 Main Str")
                .put("receiverPhone", "555-1234");

        jobRequest = jsonObject.toString();

        randomJob = new Job();
        randomJob.setId("job1");
        randomJob.setUserId("user1");
        randomJob.setCategory("Food");
        randomJob.setDescription("Deliver food to my cat");
        randomJob.setReceiverName("John Doe");
        randomJob.setReceiverAddress("123 Main St");
        randomJob.setReceiverPhone("555-1234");

        randomJob1 = new Job();

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

//    /**
//     * Test POST endpoint and serialize and deserialize objects
//     * @throws Exception
//     */
//    @Test
//    public void createJob() throws Exception {
//
//        Mockito.when(jobService.createJob(any(NewJobRequest.class))).thenReturn(randomJob);
//        Mockito.when(jobService.findJobById("job1")).thenReturn(Optional.of(randomJob));
//
//        mockMvc.perform(post("/api/jobs")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jobRequest))
//                .andExpectAll(
//                        status().isCreated(),
//                        header().string("Location", new StringContains("/api/jobs/")),
//                        jsonPath("$.id").value(randomJob.getId())
//                );
//    }

    @Test
    public void testGetJobById() throws Exception {
        String jobId = "1";

        Mockito.when(jobService.findJobById(jobId)).thenReturn(Optional.of(randomJob));

        mockMvc.perform(get("/api/jobs/{id}", jobId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(randomJob.getId()));
    }

    @Test
    public void getJobByUser() throws Exception {
        // Given User ID
        String userId = randomJob.getUserId();

        // Given list of jobs from that user
        randomJob.setStatus(JobStatus.IN_PROGRESS);
        randomJob1.setStatus(JobStatus.CANCELLED);
        List<Job> allJobs = Arrays.asList(
                randomJob, randomJob1
        );

        List<Job> onGoingJobs = Arrays.asList(
                randomJob
        );

        Mockito.when(jobService.findJobByUser(null))
                .thenReturn((allJobs));
        Mockito.when(jobService.findJobByUser(JobStatus.IN_PROGRESS))
                .thenReturn((onGoingJobs));

        mockMvc.perform(get("/api/jobs", userId))
                .andExpectAll(
                        status().isOk()
                );

        mockMvc.perform(get("/api/jobs", userId))
                .andExpectAll(
                        status().isOk()
                );
    }


    @Test
    public void testUpdateJob() throws Exception {
        String jobId = "1";

        jsonObject.put("description", "new description");

        Job updatedJob = new Job();
        updatedJob.setDescription("new description");

        Mockito.when(jobService.findJobById(jobId)).thenReturn(Optional.of(new Job()));
        Mockito.when(jobService.changeJobInfo(eq(jobId), any(NewJobRequest.class))).thenReturn(updatedJob);

        mockMvc.perform(put("/api/jobs/{id}", jobId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jobRequest))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.description").value("new description")
                );
    }


    @Test
    public void deleteJob() throws Exception {
        Mockito.when(jobService.deleteJob("job1")).thenReturn(true);

        mockMvc.perform(delete("/api/jobs/{id}", "job1"))
                .andExpect(status().isNoContent());
    }

}
