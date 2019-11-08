package com.bae.raziel.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bae.raziel.model.MySQLHostModel;
import com.bae.raziel.entity.MySQLHostEntity;


@Component
public class MySQLHostComponent {
	
	public List<MySQLHostModel> getModels(){
		return new ArrayList<MySQLHostModel>();
	} 
	
	public MySQLHostModel getModel(){
		return new MySQLHostModel();
	} 
	
	public List<MySQLHostEntity> getEntities(){
		return new ArrayList<MySQLHostEntity>();
	} 
	
	public MySQLHostEntity getEntity(){
		return new MySQLHostEntity();
	} 

}
