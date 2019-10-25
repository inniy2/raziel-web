package com.bae.raziel.command;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/console")
public class ConsoleController {
	

	@Autowired
	ConsoleService consoleService;
	
	@Autowired
	AlterRegisterService AlterRegisterService;
	
	@PostMapping("/findCluster")
	public List<ConsoleDTO> findCluster(@Valid @RequestBody ConsoleDTO consoleDTO) {
		return consoleService.findCluster(consoleDTO);
	}
	
	
	
	// validate
	
	// register
	@PostMapping("/register/ghost/alter")
	public List<AlterRegisterDTO> registerGhostAlter(@Valid @RequestBody AlterRegisterDTO alterRegisterDTO) {    	
    	
		return AlterRegisterService.registerGhostAlter(alterRegisterDTO);
	}
	
	// validate
	
	
	

}
