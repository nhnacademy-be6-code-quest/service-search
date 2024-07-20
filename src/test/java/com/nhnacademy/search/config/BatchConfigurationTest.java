package com.nhnacademy.search.config;

import com.nhnacademy.search.service.BookProductDocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatchConfigurationTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private BookProductDocumentService bookProductDocumentService;

    @InjectMocks
    private BatchConfiguration batchConfiguration;

    @Test
    void productUpdateJob_ShouldReturnJob() {
        Job job = batchConfiguration.productUpdateJob();
        assertNotNull(job);
        assertEquals("productUpdateJob", job.getName());
    }

    @Test
    void productUpdateStep_ShouldReturnStep() {
        Step step = batchConfiguration.productUpdateStep();
        assertNotNull(step);
        assertEquals("productUpdateStep", step.getName());
    }

    @Test
    void productUpdateTasklet_ShouldUpdateProducts() throws Exception {
        Tasklet tasklet = batchConfiguration.productUpdateTasklet();
        assertNotNull(tasklet);

        RepeatStatus status = tasklet.execute(null, null);
        assertEquals(RepeatStatus.FINISHED, status);

        verify(bookProductDocumentService, times(1)).productDocumentsUpdate(100);
    }
}