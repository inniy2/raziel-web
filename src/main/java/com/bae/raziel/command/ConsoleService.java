package com.bae.raziel.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

	private static final Logger logger = LoggerFactory.getLogger(ConsoleService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	ConsoleRepository consoleRepository;
	
	public List<ConsoleDTO> findCluster(ConsoleDTO consoleDTO) {
		
		String tableName = consoleDTO.getTableName();
		String clusterName = consoleDTO.getClusterName();
		String tableSchema = consoleDTO.getTableSchema();
		
		logger.debug("input data : Table Name : " + tableName);
		logger.debug("input data : Table Schema : " + tableSchema);
		logger.debug("input data : cluster Name : " + clusterName);
		
		
		
		int chooseRepository = 0;
		
		if(tableName != null && clusterName == null && tableSchema == null) {
			chooseRepository = 1;
		}else if(tableName != null && clusterName != null && tableSchema == null) {
			chooseRepository = 2;
		}else if(tableName != null && clusterName == null && tableSchema != null) {
			chooseRepository = 3;
		}else if(tableName != null && clusterName != null && tableSchema != null) {
			chooseRepository = 4;
		}
		
		List<ConsoleDTO> consoleDTOList = null;
		
		List<String[]> resultSet = null;
		
		switch(chooseRepository) {
			case 0 : break;
			case 1 :
				consoleDTOList = consoleRepository.findCluster(tableName);
				// resultSet.forEach( e -> resultSet.add(new ConsoleDTO(tableName, e[0], Integer.valueOf(e[1]) )));
					break;
			case 2 :
				consoleDTOList = consoleRepository.findClusterByClusterName(tableName,clusterName);
				// resultSet.forEach( e -> consoleDTOList.add(new ConsoleDTO(tableName, e[0], e[1], Integer.valueOf(e[2]) )));
					break;
			case 3 :
				consoleDTOList = consoleRepository.findClusterByTableSchema(tableName,tableSchema);
				// resultSet.forEach( e -> consoleDTOList.add(new ConsoleDTO(tableName, e[0], e[1], Integer.valueOf(e[2]) )));
					break;
			case 4 :
				consoleDTOList = consoleRepository.findTableAlterInfo(tableName,clusterName, tableSchema);
				// resultSet.forEach( e -> consoleDTOList.add(new ConsoleDTO(tableName, e[0], e[1], Integer.valueOf(e[2]) )));
					break;
			default :
		}
		
		
		return consoleDTOList;
		
	}
	
	
	
}
