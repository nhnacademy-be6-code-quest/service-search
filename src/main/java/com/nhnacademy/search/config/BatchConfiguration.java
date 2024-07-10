package com.nhnacademy.search.config;

import com.nhnacademy.search.service.BookProductDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BookProductDocumentService bookProductDocumentService;

    @Bean
    public Job productUpdateJob() {
        return new JobBuilder("productUpdateJob", jobRepository)
                .start(productUpdateStep())
                .build();
    }

    @Bean
    public Step productUpdateStep() {
        return new StepBuilder("productUpdateStep", jobRepository)
                .tasklet(productUpdateTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet productUpdateTasklet() {
        return ((contribution, chunkContext) -> {
            bookProductDocumentService.productDocumentsUpdate(100);
            return RepeatStatus.FINISHED;
        });
    }
}