package com.bae.raziel.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AlterRegisterService {
	
	private static final Logger logger = LoggerFactory.getLogger(AlterRegisterService.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

   
    @Autowired
    AlterRegisterRepository alterRegisterRepository;
    
    
    
    public List<AlterRegisterDTO> registerGhostAlter(AlterRegisterDTO alterRegisterDTO){
    	
    	String tableName = alterRegisterDTO.getTableName();
    	String clusterName = alterRegisterDTO.getClusterName();
    	String tableSchema = alterRegisterDTO.getTableSchema();
    	
    	alterRegisterRepository.save(alterRegisterDTO);
    	
    	return alterRegisterRepository.findIdByPrimary(tableName, clusterName, tableSchema);
 
    	
    }
    
    
    

    
}
