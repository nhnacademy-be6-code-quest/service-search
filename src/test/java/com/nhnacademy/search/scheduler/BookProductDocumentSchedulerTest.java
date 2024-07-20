package com.nhnacademy.search.scheduler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookProductDocumentSchedulerTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job productUpdateJob;

    @InjectMocks
    private BookProductDocumentScheduler scheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRunJob_Successful() throws Exception {
        // When
        scheduler.runJob();

        // Then
        verify(jobLauncher, times(1)).run(eq(productUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_JobExecutionAlreadyRunningException() throws Exception {
        // Given
        doThrow(new JobExecutionAlreadyRunningException("Job is already running"))
                .when(jobLauncher).run(eq(productUpdateJob), any(JobParameters.class));

        // When
        scheduler.runJob();

        // Then
        verify(jobLauncher, times(1)).run(eq(productUpdateJob), any(JobParameters.class));
    }

    @Test
    void testRunJob_OtherExceptions() throws Exception {
        // Given
        doThrow(new RuntimeException("Unexpected error"))
                .when(jobLauncher).run(eq(productUpdateJob), any(JobParameters.class));

        // When
        scheduler.runJob();

        // Then
        verify(jobLauncher, times(1)).run(eq(productUpdateJob), any(JobParameters.class));
    }
}