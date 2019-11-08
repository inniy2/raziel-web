package com.bae.raziel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.model.AnsibleModel;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.service.AnsibleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ansible")
public class AnsibleController {
	
	@Autowired
	AnsibleService ansibleService;
	
	@PostMapping("/gettableinfo")
	public List<String> gettableinfo(@Valid @RequestBody AnsibleModel model){
		/*
		 * requirement
		 * clusterName
		 * ghostHostname
		 * port
		 * tableName
		 */
		return ansibleService.gettableinfo(model);
	}

}
