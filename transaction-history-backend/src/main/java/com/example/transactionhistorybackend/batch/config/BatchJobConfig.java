package com.example.transactionhistorybackend.batch.config;

import com.example.transactionhistorybackend.batch.processor.TransactionItemProcessor;
import com.example.transactionhistorybackend.batch.writer.TransactionItemWriter;
import com.example.transactionhistorybackend.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchJobConfig {

    private final TransactionItemProcessor transactionItemProcessor;
    private final TransactionItemWriter transactionItemWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public FlatFileItemReader<Transaction> reader() {
        return new FlatFileItemReaderBuilder<Transaction>()
                .name("transactionItemReader")
                .resource(new ClassPathResource("dataSource.txt"))
                .delimited()
                .delimiter("|")
                .names("ACCOUNT_NUMBER", "TRX_AMOUNT", "DESCRIPTION", "TRX_DATE", "TRX_TIME", "CUSTOMER_ID")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {{
                    setTargetType(Transaction.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public Job importTransactionJob() {
        return new JobBuilder("importTransactionJob", jobRepository)
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<Transaction, Transaction>chunk(10, transactionManager)
                .reader(reader())
                .processor(transactionItemProcessor)
                .writer(transactionItemWriter)
                .build();
    }
}
