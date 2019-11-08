package com.bae.raziel.repository;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.model.TargetMySQLModel;



@Component
public class TargetMySQLRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TargetMySQLRepository.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Value("${console.mysql.user}")
	private String consoleMySQLUser;

	@Value("${console.mysql.password}")
	private String consoleMySQLpassword;
	
	
	
	/*
	 * Working as a component as well
	 */
	public MySQLEntity getEntity() {
		return new MySQLEntity();
	}
	
	public List<MySQLEntity> getEntities() {
		return new ArrayList<MySQLEntity>();
	}
	
	public TargetMySQLModel getModel() {
		return new TargetMySQLModel();
	}
	
	public List<TargetMySQLModel> getModels() {
		return new ArrayList<TargetMySQLModel>();
	}
	
	
	
	/*
	 * GHC table can not be there 
	 */
	public MySQLEntity getGhc(MySQLEntity mySQLDao) throws Exception{
		
		
		Connection con = null;

		MySQLEntity result = new MySQLEntity();
		
		try {
			
			
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+mySQLDao.getHostName()+":"+mySQLDao.getPort()+"/"+mySQLDao.getTableSchema(),consoleMySQLUser,consoleMySQLpassword);
			
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select t.`id`, t.`last_update`, t.`hint`, t.`value` from `_bae_tbl1_ghc` t join ( select max(`id`) as `id` from `bae_database`.`_bae_tbl1_ghc`) r on r.`id` = t.`id`");  
			
			while(rs.next()){
				
				result.setHostName(mySQLDao.getHostName());
				result.setTableSchema(mySQLDao.getTableSchema());
				result.setTableName(mySQLDao.getTableName());
				result.setId(rs.getLong("id"));
				result.setLastUpdate(rs.getTimestamp("last_update"));
				result.setHint(rs.getString("hint"));
				result.setValue(rs.getString("value"));
				
				logger.debug("-------------------------------------------------------------------");
				logger.debug("GHC Host name: "+ mySQLDao.getHostName());
				logger.debug("GHC Table Schema: "+ mySQLDao.getTableSchema());
				logger.debug("GHC Table Name: "+ mySQLDao.getTableName());
				logger.debug("GHC id: "+ rs.getLong("id"));
				logger.debug("GHC last update: "+ rs.getTimestamp("last_update"));
				logger.debug("GHC hint: "+ rs.getString("hint"));
				logger.debug("GHC value: "+ rs.getString("value"));
				logger.debug("-------------------------------------------------------------------");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	public List<MySQLEntity> getTableInfo(MySQLEntity entity){
		
		
		Connection con = null;
		
		List<MySQLEntity> entities = new ArrayList<MySQLEntity>();
		
		try {
			
			
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+entity.getHostName()+":"+entity.getPort()+"/information_schema",consoleMySQLUser,consoleMySQLpassword);
			
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select table_schema, table_name, ((data_length + index_length + data_free)/1024)as data_length from information_schema.tables where table_name = '"+entity.getTableName()+"'");  
			
			while(rs.next()){
				MySQLEntity result = new MySQLEntity();
				result.setHostName(entity.getHostName());
				result.setTableSchema(rs.getString("table_schema"));
				result.setTableName(rs.getString("table_name"));
				result.setTableLength(rs.getLong("data_length"));
				entities.add(result);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return entities;
	}
	
	public List<MySQLEntity> getTableInfoByTableSchema(MySQLEntity entity){
		
		
		Connection con = null;
		
		List<MySQLEntity> entities = new ArrayList<MySQLEntity>();
		
		try {
			
			logger.debug(entity.getTableSchema());
			
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+entity.getHostName()+":"+entity.getPort()+"/information_schema",consoleMySQLUser,consoleMySQLpassword);
			
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select table_schema, table_name, ((data_length + index_length + data_free)/1024)as data_length from information_schema.tables where table_name = '"+entity.getTableName()+"' AND table_schema = '" +entity.getTableSchema() +"'");  
			
			while(rs.next()){
				MySQLEntity result = new MySQLEntity();
				result.setHostName(entity.getHostName());
				result.setTableSchema(rs.getString("table_schema"));
				result.setTableName(rs.getString("table_name"));
				result.setTableLength(rs.getLong("data_length"));
				entities.add(result);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return entities;
	}
	
	
	public MySQLEntity getReadOnly(MySQLEntity entity){
		
		
		Connection con = null;

		MySQLEntity result = new MySQLEntity();
		
		
		try {
			
			
			
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+entity.getHostName()+":"+entity.getPort()+"/information_schema",consoleMySQLUser,consoleMySQLpassword);
			
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("show variables like 'read_only'");  
			
			while(rs.next()){
				result.setHostName(entity.getHostName());
				result.setReadOnly(rs.getString("Value"));
				
				logger.debug("-------------------------------------------------------------------");
				logger.debug("Host name: "+ entity.getHostName());
				logger.debug("Read Only: "+ rs.getString("Value"));
				logger.debug("-------------------------------------------------------------------");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

}
