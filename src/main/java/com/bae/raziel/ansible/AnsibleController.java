package com.bae.raziel.ansible;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.ghost.GhostDto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ansible")
public class AnsibleController {
	
	@Autowired
	AnsibleService ansibleService;
	
	@PostMapping("/find")
	public List<String> save(@Valid @RequestBody GhostDto ghostDto){
		return ansibleService.find(ghostDto);
	}

}
