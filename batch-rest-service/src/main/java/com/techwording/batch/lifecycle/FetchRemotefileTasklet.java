package com.techwording.batch.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FetchRemotefileTasklet implements Tasklet {

	private static final Logger logger = LoggerFactory.getLogger(FetchRemotefileTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		logger.info("Executing FetchRemotefileTasklet step");
		return RepeatStatus.FINISHED;
	}

}
