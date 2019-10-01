package ru.ezhov.general.service.infrastructure.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ezhov.general.service.domain.model.Part;
import ru.ezhov.general.service.infrastructure.batch.part.PartItemProcessor;

import javax.sql.DataSource;
import java.util.Arrays;

//http://java-is-everywhere.blogspot.com/2016/11/how-to-execute-spring-batch-job.html
//https://spring.io/guides/gs/batch-processing/

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ListItemReader<String> reader() {
        return new ListItemReader<String>(Arrays.asList("1", "1"));
    }

    @Bean
    public PartItemProcessor processor() {
        return new PartItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Part> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Part>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO PARTS (part) VALUES (:value)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Part> writer) {
        return stepBuilderFactory.get("step1")
                .<String, Part>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
