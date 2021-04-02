package com.techwording.batch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class JobInvoker {

	private static final Logger logger = LoggerFactory.getLogger(JobInvoker.class);

	public Long execute(JobLauncher jobLauncher, Job job, String filePath) {

		Long jobId = null;
		logger.info("Building job params");
		JobParameters params = new JobParametersBuilder()
			.addString("JobID", String.valueOf(System.currentTimeMillis()))
			.addString("fileName", filePath)
			.toJobParameters();
		try {
			logger.info("Invoking job");
			JobExecution execution = jobLauncher.run(job, params);
			jobId = execution.getJobId();
		}
		catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			logger.error("Error occured while trying to invoke the job", e);
			throw new IllegalStateException(e.getMessage(), e);
		}
		return jobId;
	}

}
