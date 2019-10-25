package com.bae.raziel.login;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	@Autowired
	LoginRepository loginRepository;
	
	
	public LoginDto login(LoginDto loginDto){
		
		Optional<LoginEntity> loginEntity = loginRepository.findById(loginDto.getRazielUser());		
		
		if(loginEntity.isPresent()) {
			if(loginEntity.get().getRazielPassword().equals(loginDto.getRazielPassword())){
				loginDto.setStatus(1); // login 
				return loginDto;
			}else {
				loginDto.setStatus(2); // wrong password 
				return loginDto;
			}
		}else{
			loginDto.setStatus(3); // no user 
			return loginDto;
		}
		
	}
	
}
