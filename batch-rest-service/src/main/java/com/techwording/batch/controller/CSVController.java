package com.techwording.batch.controller;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techwording.batch.util.CSVHelper;
import com.techwording.batch.util.JobInvoker;

@RestController
public class CSVController {

	private static final Logger logger = LoggerFactory.getLogger(CSVController.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping(path = "/api/csv/upload", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

		logger.info("Trying to upload the input file : " + file.getName());
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				String destination = "target/" + toHexString(file.getBytes()) + "/" + file.getOriginalFilename();
				Path path = Paths.get("target/" + toHexString(file.getBytes()));
				Files.createDirectories(path);
				logger.info("Creating file at : " + destination);
				Files.copy(file.getInputStream(), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
				Long jobId = new JobInvoker().execute(jobLauncher, job, destination);
				logger.info("Job created succesfully with jobId : " + jobId);
				message = "Uploaded the file successfully: " + file.getOriginalFilename() + ", JobId : " + jobId;

				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage(message));
			}
			catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				logger.error("Error occured while uploading the file or invoking the job", e);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(new ResponseMessage(message));
	}

	public static String toHexString(byte[] hash) {

		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}
		return hexString.toString();
	}

}

class ResponseMessage {

	private String message;

	public ResponseMessage(String message) {

		this.message = message;// TODO Auto-generated constructor stub
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

}
