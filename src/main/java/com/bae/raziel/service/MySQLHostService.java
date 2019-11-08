package com.bae.raziel.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bae.raziel.component.MySQLHostComponent;
import com.bae.raziel.entity.LoginEntity;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.entity.MySQLHostEntity;
import com.bae.raziel.model.LoginModel;
import com.bae.raziel.model.MySQLHostModel;
import com.bae.raziel.repository.MySQLHostRepository;
import com.bae.raziel.repository.TargetMySQLRepository;



@Service
public class MySQLHostService {

	private static final Logger logger = LoggerFactory.getLogger(MySQLHostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	@Autowired
	MySQLHostComponent mySQLHostComponent;

		
	
	
	
	public void save(List<MySQLHostModel> models) {
		
		List<MySQLHostEntity>  entities  = mySQLHostComponent.getEntities();
		models.forEach(e -> {
			MySQLHostEntity entity = mySQLHostComponent.getEntity();
			entity.setMysqlHostName(e.getHostName());
			entity.setClusterName(e.getClusterName());
			entity.setHostType(e.getHostType());
			entities.add(entity);
		});
		
		
		mySQLHostRepository.saveAll(entities);
	
	}
	
	
	/*
	 * for updateGhostHost
	 */
	
	
	/*
	 * for updateReadOnly
	 */
	
	


	public List<MySQLHostModel> findAll() {
		
		List<MySQLHostEntity> entities = mySQLHostRepository.findAll();
		List<MySQLHostModel> models = mySQLHostComponent.getModels();
		
		entities.forEach(e -> {
			MySQLHostModel model = mySQLHostComponent.getModel();
			model.setClusterName(e.getClusterName());
			model.setHostName(e.getMysqlHostName());
			model.setHostType(e.getHostType());
			models.add(model);
		});
		
		return models;
	}


	public void deleteById(@Valid String id) {
		mySQLHostRepository.deleteById(id);
		
	} 
	
	
}
