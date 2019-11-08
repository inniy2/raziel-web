package com.bae.raziel.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.model.LoginModel;
import com.bae.raziel.service.LoginService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

	
	@Autowired
	LoginService loginService;
	
	
	@GetMapping("/findAll")
	public List<LoginModel> findAll(){
		return loginService.findAll();
	}
	
	@PostMapping("/save")
	public void save(@Valid @RequestBody List<LoginModel> loginModels){
		loginService.save(loginModels);
	}
	
	@GetMapping("/delete")
	public void deleteById(@Valid @RequestParam String id){
		loginService.deleteById(id);
	}
	
	
	
	
	
	@PostMapping("/login")
	public LoginModel login(@Valid @RequestBody LoginModel loginModel){
		return loginService.login(loginModel);
	}
	
}
