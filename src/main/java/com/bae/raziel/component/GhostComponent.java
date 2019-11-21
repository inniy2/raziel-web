package com.bae.raziel.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.ArrayUtils;

import com.bae.raziel.entity.AlterHistEntity;

import com.bae.raziel.model.GhostModel;



@Component
public class GhostComponent {

	private static final Logger logger = LoggerFactory.getLogger(GhostComponent.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	String[] ghostRunComandTmplate = new String[] {
			"gh-ost",                                    // 0
			"--max-load=Threads_running=50",             // 1
			"--critical-load=Threads_running=1500",      // 2
			"--chunk-size=500",                          // 3
			"--max-lag-millis=1500",                     // 4
			"--conf=debian.cnf",                         // 5
			"--host=",                                   // 6
			"--throttle-control-replicas=",              // 7
			"--database=",                               // 8
			"--table=",                                  // 9
			"--alter=",                                  // 10
			"--switch-to-rbr",                           // 11
			"--cut-over=default",                        // 12
			"--postpone-cut-over-flag-file=/tmp/ghost.postpone.flag", // 13
			"--exact-rowcount",                          // 14
			"--concurrent-rowcount",                     // 15
			"--default-retries=120",                     // 16
			"--timestamp-old-table",                     // 17
			""                                           // 18
	};
	
	
	
	public List<String> getList(){
		return new ArrayList<String>();
	}
	
	
	
	
	public List<String> ghostRun(GhostModel model, String verbose, boolean cutOver) {
		
		String[] ghostRunComand = ghostRunComandTmplate;
		
		StringBuilder database      = new StringBuilder("--database=");
		StringBuilder host          = new StringBuilder("--host=");
		StringBuilder table         = new StringBuilder("--table=");
		StringBuilder alterStatment = new StringBuilder("--alter=");
		StringBuilder checkReplica  = new StringBuilder("--throttle-control-replicas=");
		
		
		
		
		/*
		 * database
		 */
		database.append(model.getTableSchema());

		
		/*
		 * host
		 */
		host.append(model.getGhostHostName());
		
		/*
		 * table
		 */
		table.append(model.getTableName());
		
		/*
		 * checkReplica
		 */
		ArrayList<String> checkReplicaList = model.getCheckReplicaList();
		for(int i = 0; i < checkReplicaList.size(); i++) {			
			if (i != 0) checkReplica.append(",");
			checkReplica.append(checkReplicaList.get(i));
		}
		
		
		/*
		 * alterStatment
		 */
		ArrayList<String> alterStatementList = model.getAlterStatement();
		for(int i = 0; i < alterStatementList.size(); i++) {			
			if (i != 0) alterStatment.append(",");
			alterStatment.append(alterStatementList.get(i));
		}
		
		
		
		for(int i= 0; i < ghostRunComand.length; i++) {
			if(i == 6) {        ghostRunComand[i] = host.toString();
			}else if (i == 8) { ghostRunComand[i] = database.toString();
			}else if (i == 9) { ghostRunComand[i] = table.toString();
			}else if (i == 7) { ghostRunComand[i] = checkReplica.toString();
			}else if (i == 10){ ghostRunComand[i] = alterStatment.toString();
			}else if (i == 18){ ghostRunComand[i] = verbose;
			}
		
			logger.info(ghostRunComand[i]);
		}
		
		ghostRunComand = (!cutOver)?(String[]) ArrayUtils.remove(ghostRunComand, 13):ghostRunComand;
	
		logger.debug("cmd option length: "+ghostRunComand.length);
		logger.debug("cut over : "+cutOver);
		
		List<String> dryRunCpml = this.runProcess(ghostRunComand);
		
		/*
		 * Find "FATAL" word from dryrun result
		 */
		List<String> fatalStr = dryRunCpml.stream()
				.filter(e -> e.matches("FATAL"))
				.map(e -> e.toString())
				.collect(Collectors.toList());
		
		
		return (fatalStr.isEmpty())? dryRunCpml : fatalStr;
		
	}
	
	
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
				outputStrList.add(errorLine);
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




	public List<GhostModel> getModels() {
		// TODO Auto-generated method stub
		return new ArrayList<GhostModel>();
	}




	public GhostModel getModel() {
		// TODO Auto-generated method stub
		return new GhostModel();
	}
	
	
	
	



	public AlterHistEntity getAlterHistEntity() {
		// TODO Auto-generated method stub
		return new AlterHistEntity();
	}




	public boolean isValidateTableName(String tableName) {
		
		String prefix = String.valueOf((tableName.charAt(0)));
		String postfix = tableName.substring(tableName.length() - 4, tableName.length());
		
		logger.debug("prefix : " + prefix);
		logger.debug("postfix : " + postfix);
		
		return (prefix.equals("_") && ( postfix.equals("_ghc") || postfix.equals("_gho") ));
	}




	
	
}
