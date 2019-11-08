package com.bae.raziel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.model.GhostModel;
import com.bae.raziel.service.GhostService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ghost")
public class GhostController {

	@Autowired
	GhostService ghostService;
	
	
	/*
	 * requirement
	 * clusterName
	 * ghostHostName
	 * checkReplicaList
	 * tableName
	 * TableSchema
	 * alterStatement
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
	 */
	@PostMapping("/execute")
	public void execute(@Valid @RequestBody GhostModel model){
		ghostService.execute(model);
	}
	

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
	
}
