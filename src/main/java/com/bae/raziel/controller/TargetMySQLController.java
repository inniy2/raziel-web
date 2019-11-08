package com.bae.raziel.controller;



import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.bae.raziel.model.TargetMySQLModel;
import com.bae.raziel.service.TargetMySQLService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/targetmysql")
public class TargetMySQLController {
	
	private static final Logger logger = LoggerFactory.getLogger(TargetMySQLController.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	@Autowired
	TargetMySQLService targetMySQLHostService;
	
	
	/*
	 * requirement
	 * hostName
	 * port
	 */
	@PostMapping("/getreadonly") 
	public TargetMySQLModel getReadOnly(@Valid @RequestBody TargetMySQLModel model){
		 return targetMySQLHostService.getReadOnly(model);
	}
	
	
	/*
	 * requirement
	 * hostName
	 * port
	 * tableName
	 * optional - tableSchema
	 */
	@PostMapping("/gettableinfo")
	public List<TargetMySQLModel> gettableinfo(@Valid @RequestBody TargetMySQLModel model){
		 return targetMySQLHostService.getTableInfo(model);
	}
	

}
