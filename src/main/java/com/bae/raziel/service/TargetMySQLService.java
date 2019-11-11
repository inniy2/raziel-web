package com.bae.raziel.service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.model.TargetMySQLModel;
import com.bae.raziel.repository.TargetMySQLRepository;

@Service
public class TargetMySQLService {

	private static final Logger logger = LoggerFactory.getLogger(TargetMySQLService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	TargetMySQLRepository targetMySQLRepository;
	
	public TargetMySQLModel getReadOnly(TargetMySQLModel model) {

		MySQLEntity entity = targetMySQLRepository.getEntity();		
		entity.setHostName(model.getHostName());
		entity.setPort(model.getPort());
		
		entity =  targetMySQLRepository.getReadOnly(entity);
		model.setReadOnly(entity.getReadOnly());
		
		return model;
	}
	
	public MySQLEntity getReadOnly(String hostName, int port) {
		
		return targetMySQLRepository.getReadOnly(hostName, port);
		
	}

	public List<TargetMySQLModel> getTableInfo(@Valid TargetMySQLModel model) {
		
		MySQLEntity entity = targetMySQLRepository.getEntity();		
		entity.setHostName(model.getHostName());
		entity.setPort(model.getPort());
		entity.setTableName(model.getTableName());
		
		List<MySQLEntity> entities = null;
		if(model.getTableSchema() == null) {
			entities =  targetMySQLRepository.getTableInfo(entity);
		}else {
			entity.setTableSchema(model.getTableSchema());
			entities =  targetMySQLRepository.getTableInfoByTableSchema(entity);
		}
		
		List<TargetMySQLModel> models = targetMySQLRepository.getModels();
		entities.forEach(e-> {
			 TargetMySQLModel rtnModel = targetMySQLRepository.getModel();
			 rtnModel.setHostName(model.getHostName());
			 rtnModel.setTableName(model.getTableName());
			 rtnModel.setTableSchema(e.getTableSchema());
			 rtnModel.setTableLength(e.getTableLength());
			 models.add(rtnModel);
		});
		
		return models;
	}

}
