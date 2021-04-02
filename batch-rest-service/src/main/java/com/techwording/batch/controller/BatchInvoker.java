package com.techwording.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techwording.batch.util.JobInvoker;

@RestController
public class BatchInvoker {

	private static final Logger logger = LoggerFactory.getLogger(BatchInvoker.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping(path = "/job/invoke", method = RequestMethod.GET)
	public String invoke() {

		Long jobId = null;
		try {
			logger.info("Invoking job");
			jobId = new JobInvoker().execute(jobLauncher, job, "src/main/resources/customer-data.csv");
		}
		catch (IllegalStateException e) {
			logger.error("Error occured while trying to invoke the job", e);
			return "failure";
		}

		return "success , jobId : " + jobId;

	}

}
