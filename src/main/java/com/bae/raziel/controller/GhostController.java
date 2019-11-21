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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.domain.AlterCount;
import com.bae.raziel.entity.AlterHistEntity;
import com.bae.raziel.entity.AlterStatusEntity;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.model.GhcModel;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.service.GhostService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ghost")
public class GhostController {
	private static final Logger logger = LoggerFactory.getLogger(GhostController.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	

	@Autowired
	GhostService ghostService;
	
	
	/*
	 * requirement
	 * clusterName
	 * ghostHostName
	 * checkReplicaList
	 * tableName
	 * tableSchema
	 * alterStatement
	 * cutOver
	 */
	@PostMapping("/dryrun")
	public List<String> dryRun(@Valid @RequestBody GhostModel model){
		return ghostService.dryRun(model);
	}
	
	
	/*
	 * requirement
	 * clusterName
	 * ghostHostName
	 * checkReplicaList
	 * tableName
	 * TableSchema
	 * cutOver
	 * registerEmail
	 */
	@PostMapping("/execute")
	public String execute(@Valid @RequestBody GhostModel model){
		logger.debug(model.getGhostHostName());
		return ghostService.execute(model);
	}
	

	@GetMapping("/findAlterStatusAll")
	public List<AlterStatusEntity> findAlterStatusAll(){
		return ghostService.findAlterStatusAll();
	}
	
	@GetMapping("/findAlterHistAll")
	public List<AlterHistEntity> findAlterHistAll(){
		return ghostService.findAlterHistAll();
	}
	
	@GetMapping("/findAlterHistByDay")
	public List<AlterCount> findAlterHistByDay(@Valid @RequestParam String day){
		return ghostService.findAlterHistByDay(day);
	}
	
	@PostMapping("/dropShadowTable")
	public String dropShadowTable(@Valid @RequestBody GhostModel model) {
		String clusterName = model.getClusterName();
		String tableSchema = model.getTableSchema();
		String tableName = model.getTableName();
		return ghostService.dropShadowTable(clusterName, tableSchema, tableName);
	}
	
	@PostMapping("/progress")
	public GhcModel progress(@Valid @RequestBody GhostModel model) {
		String clusterName = model.getClusterName();
		String tableSchema = model.getTableSchema();
		String tableName = model.getTableName();
		return ghostService.progress(clusterName, tableSchema, tableName);
	}
	
	
	
	/*
	@PostMapping("/findAll")
	public List<GhostModel> findAll(@Valid @RequestBody GhostModel model){
		return ghostService.findAll(model);
	}
	

	@PostMapping("/findAllByProgress")
	public List<GhostModel> findAllByProgress(@Valid @RequestBody GhostModel model){
		return ghostService.findAllByProgress(model);
	}
	
	@PostMapping("/findGHCByProgress")
	public GhostModel findGHCByProgress(@Valid @RequestBody GhostModel model){
		return ghostService.findGHCByProgress(model);
	}
	*/
	
}
