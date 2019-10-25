package com.bae.raziel.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class GhostComponent {

	private static final Logger logger = LoggerFactory.getLogger(GhostComponent.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	public List<String> runProcess(String[] cmd) {
		
		List<String> outputStrList = new ArrayList<String>();
		
		ProcessBuilder processBuilder = new ProcessBuilder();
			
		
		processBuilder.command(cmd);
		
		
		
		try {
			
			Process process = processBuilder.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			logger.info("------------------------------------------- inputstream");
			while ((line = reader.readLine()) != null) {
				logger.info(line);
				outputStrList.add(line);
			}
			
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine = null;
			logger.info("------------------------------------------- errorstream");
			while ((errorLine = error.readLine()) != null) {
				logger.info(errorLine);
				// outputStrList.add(errorLine);
			}
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			return outputStrList;
		}
		
		
	}
	
	public void runProcess(String[] cmd, String slience) {
		
		// List<String> outputStrList = new ArrayList<String>();
		
		ProcessBuilder processBuilder = new ProcessBuilder();
			
		
		processBuilder.command(cmd);
		
		
		
		try {
			
			Process process = processBuilder.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			logger.info("------------------------------------------- inputstream");
			while ((line = reader.readLine()) != null) {
				logger.info(line);
				// outputStrList.add(line);
			}
			
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String errorLine = null;
			logger.info("------------------------------------------- errorstream");
			while ((errorLine = error.readLine()) != null) {
				logger.info(errorLine);
				// outputStrList.add(errorLine);
			}
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			// return outputStrList;
		}
		
		
	}
	
	
}
