package com.bae.raziel.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bae.raziel.entity.LoginEntity;
import com.bae.raziel.model.LoginModel;

@Component
public class LoginComponent {
	
	
	public List<LoginModel> getLoginModelList(){
		return new ArrayList<LoginModel>();
	} 
	
	public LoginModel getLoginModel(){
		return new LoginModel();
	} 
	
	public List<LoginEntity> getLoginEntityList(){
		return new ArrayList<LoginEntity>();
	} 
	
	public LoginEntity getLoginEntity(){
		return new LoginEntity();
	} 

}
