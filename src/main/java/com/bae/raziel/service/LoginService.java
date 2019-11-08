package com.bae.raziel.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bae.raziel.component.LoginComponent;
import com.bae.raziel.entity.LoginEntity;
import com.bae.raziel.model.LoginModel;
import com.bae.raziel.repository.LoginRepository;



@Service
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	LoginComponent loginComponent;
	
	
	public LoginModel login(LoginModel loginModel){
		
		Optional<LoginEntity> loginEntity = loginRepository.findById(loginModel.getRazielUser());		
		
		if(loginEntity.isPresent()) {
			if(loginEntity.get().getRazielPassword().equals(loginModel.getRazielPassword())){
				loginModel.setStatus(1); // login 
				return loginModel;
			}else {
				loginModel.setStatus(2); // wrong password 
				return loginModel;
			}
		}else{
			loginModel.setStatus(3); // no user 
			return loginModel;
		}
		
	}
	
	
	public void save(List<LoginModel> loginModels) {
		
		List<LoginEntity> loginEntities = loginComponent.getLoginEntityList();

		loginModels.forEach(e -> {
			LoginEntity loginEntity = loginComponent.getLoginEntity();
			loginEntity.setRazielUser(e.getRazielUser());
			loginEntity.setRazielPassword(e.getRazielPassword());
			loginEntities.add(loginEntity);
		});
		
		loginRepository.saveAll(loginEntities);
	}
	
	
	public List<LoginModel> findAll(){
		
		List<LoginEntity> loginEntities = loginRepository.findAll();
		List<LoginModel> loginModels = loginComponent.getLoginModelList();
		
		loginEntities.forEach(e -> {
			LoginModel loginModel = loginComponent.getLoginModel();
			loginModel.setRazielUser(e.getRazielUser());
			loginModel.setRazielPassword(e.getRazielPassword());
			loginModels.add(loginModel);
		});
		
		return loginModels;
	}


	public void deleteById(@Valid String id) {
		loginRepository.deleteById(id);
		
	}
	
	
	
	
}
