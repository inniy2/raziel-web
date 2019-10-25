package com.bae.raziel.login;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://test-bae-t1.testdb:8000")
@RestController
@RequestMapping("/api/login")
public class LoginController {

	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/login")
	public LoginDto login(@Valid @RequestBody LoginDto loginDto){
		return loginService.login(loginDto);
	}
	
	
	
}
