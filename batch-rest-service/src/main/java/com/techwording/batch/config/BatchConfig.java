package com.techwording.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.techwording.batch.lifecycle.CustomerProcessor;
import com.techwording.batch.lifecycle.ExtensionLogicListener;
import com.techwording.batch.lifecycle.FetchRemotefileTasklet;
import com.techwording.batch.lifecycle.JsonWriterExt;
import com.techwording.batch.model.Customer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	JobRepository jobRepository;

	private static final String OVERRIDDEN_BY_EXPRESSION = null;

	@Bean
	public JobLauncher jobLauncher() throws Exception {

		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@Bean
	Job csvFileToDatabaseJob() {

		return jobBuilderFactory.get("csvFileToJSonJob")
			.incrementer(new RunIdIncrementer())
			.preventRestart()
			.listener(new ExtensionLogicListener())
			.flow(fetchRemotefileTask())
			.next(processFileChunkStep())
			.end()
			.build();
	}

	@Bean
	public Step processFileChunkStep() {

		return stepBuilderFactory
			.get("processFileChunkStep")
			.<Customer, Customer>chunk(10)
			.reader(reader(OVERRIDDEN_BY_EXPRESSION))
			.processor(csvCustomerProcessor())
			.writer(customerJsonFileWriter())
			.build();
	}

	@Bean
	public Step fetchRemotefileTask() {

		return stepBuilderFactory
			.get("FetchRemotefileTask")
			.tasklet(new FetchRemotefileTasklet())
			.build();
	}

	@Bean
	ItemProcessor<Customer, Customer> csvCustomerProcessor() {

		return new CustomerProcessor();
	}

	public JsonWriterExt<Customer> customerJsonFileWriter() {

		// Output file configured path
		String outputFile = "target/customer-output.json";

		return new JsonWriterExt<>(outputFile, "customerJsonFileWriter");

	}

	@Bean
	@StepScope
	public FlatFileItemReader<Customer> reader(@Value("#{jobParameters['fileName']}") String fileName) {

		System.out.println("Job param for fileName : " + fileName);
		// Create reader instance
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();

		// Set input file location
		reader.setResource(new FileSystemResource(fileName));

		// Set number of lines to skips. Use it if file has header rows.
		reader.setLinesToSkip(1);

		// Configure how each line will be parsed and mapped to different values
		reader.setLineMapper(new DefaultLineMapper<Customer>() {
			{
				// 3 columns in each row
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "firstName", "lastName" });
					}
				});
				// Set values in Customer class
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {
					{
						setTargetType(Customer.class);
					}
				});
			}
		});
		return reader;
	}

}
