package com.techwording.batch.lifecycle;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;

import com.techwording.batch.model.Customer;

public class ExtensionLogicListener implements ItemWriteListener<Customer> {

	@Override
	public void beforeWrite(List<? extends Customer> items) {

		System.out.println("Listener:beforeWrite ");

	}

	@Override

	public void afterWrite(List<? extends Customer> items) {

		System.out.println("Listener:afterWriter : Make rest calls from here");

	}

	@AfterJob
	public void afterJob(JobExecution execution) {

		System.out.println("Listener:afterJob: Make rest calls from here");

	}

	@Override
	public void onWriteError(Exception exception, List<? extends Customer> items) {

		System.out.println("Listener:onWriteError");

	}

}
