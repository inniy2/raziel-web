package com.bae.raziel.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bae.raziel.component.AnsibleComponent;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.entity.MySQLHostEntity;
import com.bae.raziel.model.AnsibleModel;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.repository.MySQLHostRepository;
import com.bae.raziel.repository.TargetMySQLRepository;





@Service
public class AnsibleService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnsibleService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	
	@Autowired
	AnsibleComponent ansibleComponent;
	
	
	
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	
	@Autowired
	TargetMySQLRepository targetMySQLRepository;
	
	
	
	
	public List<String> gettableinfo( AnsibleModel model){
		
		List<String> list = ansibleComponent.getList();
	
		List<MySQLHostEntity> mysqlHostentities = mySQLHostRepository.findAllMySQLHostByClusterName(model.getClusterName());
		
		if(mysqlHostentities.isEmpty()) {
			list.add("No hosts for : "+ model.getClusterName());
			return list;
		}else if(mysqlHostentities.size() == 0) {
			list.add("Hosts count is : "+ mysqlHostentities.size());
			return list;
		}
		
		
		/*
		 * Find hosts has type is 3 ( ghost host )
		 */
		boolean isGhostHostSelected = mysqlHostentities.stream()
				.anyMatch(e -> e.getHostType() == 3);
		
		String ghostHostName = null;
		
		if(isGhostHostSelected) {
			ghostHostName = mysqlHostentities.stream()
					.filter(e -> e.getHostType() == 3 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}else {
			ghostHostName = mysqlHostentities.stream()
					.filter(e -> e.getHostType() == 2 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}
		
		
		if(model.getGhostHostName() == null) model.setGhostHostName(ghostHostName);
		
		
		/*
		 * Read ghost host from admin
		 */
		MySQLHostEntity ghostEntity = (MySQLHostEntity)mySQLHostRepository.findById(model.getGhostHostName()).get();
		
		/*
		 * Read target table info from production mysql
		 */
		MySQLEntity mysqlEntity =  targetMySQLRepository.getEntity();
		mysqlEntity.setHostName(ghostEntity.getMysqlHostName());
		mysqlEntity.setPort(model.getPort());
		mysqlEntity.setTableName(model.getTableName());
	
		List<MySQLEntity> mysqlEntities = targetMySQLRepository.getTableInfo(mysqlEntity);
		
		mysqlEntities.forEach(e -> list.add(e.toString()));
		
		
		
		
		
		
		/*
		 * Read disk usage from ghost host by ansible
		 */
		String[] findDiskUsage = new String[] {
				"ansible",                            // 0
				"-i",                                 // 1
				"hosts",                              // 2
				ghostEntity.getClusterName(),         // 3
				"--become",                           // 4
				"-m",                                 // 5
				"shell",                              // 6
				"-a"                                  // 7
				+"df /mysql",                         // 8
				
		};
		
		logger.info("------------------------------------------- Ansible log");
		
		List<String> findDiskUsageList = Arrays.asList(findDiskUsage);
		
		
		findDiskUsageList.forEach(e -> logger.info(e.toString()));
		
		
		List<String> diskUsageList =  ansibleComponent.findDiskUsage(findDiskUsage);
		
		diskUsageList.forEach(e -> list.add(e));
		
		return  list;
		
		
	}
	
	
	
	

}
