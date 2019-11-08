package com.bae.raziel.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.model.MySQLHostModel;
import com.bae.raziel.service.MySQLHostService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mysqlhost")
public class MySQLHostController {

	
	@Autowired
	MySQLHostService mySQLHostService;

	
	
	
	@GetMapping("/findAll")
	public List<MySQLHostModel> findAll(){
		 return mySQLHostService.findAll();
	}
	
	
	@PostMapping("/save")
	public void save(@Valid @RequestBody List<MySQLHostModel> models){
		mySQLHostService.save(models);
	}
	
	
	@GetMapping("/deleteById")
	public void deleteById(@Valid @RequestParam String id){
		mySQLHostService.deleteById(id);
	}
	
	
	
	
}
