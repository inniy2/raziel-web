package com.bae.raziel.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bae.raziel.component.EntityManagerComponent;
import com.bae.raziel.component.GhostComponent;
import com.bae.raziel.domain.AlterCount;
import com.bae.raziel.domain.TableInfo;
import com.bae.raziel.entity.AlterHistEntity;
import com.bae.raziel.entity.AlterStatusEntity;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.entity.MySQLHostEntity;
import com.bae.raziel.model.GhcModel;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.multipk.AlterStatusEntityId;
import com.bae.raziel.repository.AlterHistRepository;
import com.bae.raziel.repository.AlterStatusRepository;
import com.bae.raziel.repository.MySQLHostRepository;
import com.bae.raziel.repository.TargetMySQLRepository;


@Service
public class GhostService {
	
	private static final Logger logger = LoggerFactory.getLogger(GhostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	@Autowired
	TargetMySQLService targetMySQLHostService;

	@Autowired
	GhostComponent ghostComponent;
	
	
	@Autowired
	AlterStatusRepository alterStatusRepository;
	
	@Autowired
	AlterHistRepository alterHistRepository;
	
	/*
	 * To get ghost host name & check replica list
	 */
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	
	/*
	 * To get GHC info
	 */
	@Autowired
	TargetMySQLRepository targetMySQLRepository;
	
	
	/*
	 * When need to use custom SQL
	 */
	@Autowired
	EntityManagerComponent entityManagerComponent;
	
	
	@Value("${console.mysql.user}")
	private String consoleMySQLUser;

	@Value("${console.mysql.password}")
	private String consoleMySQLpassword;
	

	
	
	
	
	
	
	
	public GhcModel progress(String clusterName, String tableSchema, String tableName) {
		
		
		List<MySQLHostEntity> entities = mySQLHostRepository.findAllMySQLHostByClusterName(clusterName);
	
		/*
		 * Find hosts has type is 3 ( ghost host )
		 */
	
		boolean isGhostHostSelected = entities.stream()
				.anyMatch(e -> e.getHostType() == 3);
		
		String ghostHostName = null;
		
		if(isGhostHostSelected) {
			ghostHostName = entities.stream()
					.filter(e -> e.getHostType() == 3 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}else {
			ghostHostName = entities.stream()
					.filter(e -> e.getHostType() == 2 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}
		

		GhcModel model = null;
		try {
			
			model = targetMySQLRepository.getGhc(ghostHostName, 3306, tableSchema, tableName);
			
			model.setServiceMessage("OK");
			
		}catch(NullPointerException ne) {
			     
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			model.setServiceMessage(e1.getMessage());
		}
		
		
		return model;
	}
	
	
	
	public List<GhostModel> findAllAlterStatus(){
		
		List<GhostModel> models = ghostComponent.getModels();
		
		List<AlterStatusEntity> entities = alterStatusRepository.findAll();
		
		
		entities.forEach(e -> {
			GhostModel model = ghostComponent.getModel();
			model.setOrderId(entities.indexOf(e));
			model.setClusterName(e.getClusterName());
			model.setTableSchema(e.getTableSchema());
			model.setTableName(e.getTableName());
			model.setGhostHostName(e.getGhostHostName());
			//model.setCheckReplicaList(e.getCheckReplicaList());
			//model.setAlterStatement(e.getAlterStatement());
			model.setRegisterEmail(e.getRegisterEmail());
			model.setCreateTimestamp(e.getRegisterTimestamp());
			models.add(model);
		});
		
		return models;
		
	}
	
	public List<GhostModel> findAllAlterHist(){
		
		List<GhostModel> models = ghostComponent.getModels();
		
		List<AlterHistEntity> entities = alterHistRepository.findAll();
		
		
		entities.forEach(e -> {
			GhostModel model = ghostComponent.getModel();
			model.setOrderId(e.getId());
			model.setClusterName(e.getClusterName());
			model.setTableSchema(e.getTableSchema());
			model.setTableName(e.getTableName());
			model.setGhostHostName(e.getGhostHostName());
			//model.setCheckReplicaList(e.getCheckReplicaList());
			//model.setAlterStatement(e.getAlterStatement());
			model.setRegisterEmail(e.getRegisterEmail());
			model.setCreateTimestamp(e.getRegisterTimestamp());
			model.setUpdateTimestamp(e.getCreateTimestamp());
			models.add(model);
		});
		
		return models;
		
	}
	
	/*
	public List<GhostModel> findAllByProgress(GhostModel ghostDto){
		
		List<GhostModel> ghostDtoList = new ArrayList<GhostModel>();
		
		List<AlterStatusEntity> ghostEntityList = ghostRepository.findHistoryAllByProgressStatus(ghostDto.getProgressStatus());
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	*/
	
	
	
	public List<String> dryRun(GhostModel model){
		logger.info("------------------- Dryrun start -------------------");
		logger.info("1. Get Target host by cluster name : " + model.getClusterName());
		
		List<String> list = ghostComponent.getList();
		
		List<MySQLHostEntity> entities = mySQLHostRepository.findAllMySQLHostByClusterName(model.getClusterName());
		
		
		
		if(entities.isEmpty()) {
			list.add("No hosts for : "+ model.getClusterName());
			return list;
		}else if(entities.size() == 0) {
			list.add("Hosts count is : "+ entities.size());
			return list;
		}
		
		
		logger.info("2. Update read_only status: " + model.getClusterName());
		/*
		 * Update read_only 
		 */
		entities.forEach(e -> {
			MySQLEntity mysqlEntity = targetMySQLHostService.getReadOnly(e.getMysqlHostName(), 3306);
			MySQLHostEntity hostEntity = new MySQLHostEntity();
			hostEntity.setClusterName(e.getClusterName());
			hostEntity.setMysqlHostName(e.getMysqlHostName());
			hostEntity.setHostType((mysqlEntity.getReadOnly().equals("OFF"))? 1:2);
			logger.info("Update read_only status: " + e.toString());
			mySQLHostRepository.save(hostEntity);
		});
		
		
		
		logger.info("3. Update ghost host: " + model.getGhostHostName());
		/*
		 * Update ghost host
		 */
		if(model.getGhostHostName() != null) {
			Optional<MySQLHostEntity> optinal = mySQLHostRepository.findById(model.getGhostHostName());
			MySQLHostEntity entity = null;
			if(optinal.isEmpty()) {
				list.add("No ghost hosts for : "+ model.getGhostHostName());
				return list;
			}else {
				entity = optinal.get();
			}
			
			entity.setHostType(3);
			logger.info("Update ghost host: " + entity.getMysqlHostName());
			mySQLHostRepository.save(entity);
		}
		
		
		/*
		 * Find hosts which type is 1 ( master )
		 */
		List<String> masterHostName = entities.stream()
				.filter(e -> e.getHostType() == 1 )
				.map(q -> q.getMysqlHostName())
				.collect(Collectors.toList());
		
		/*
		 * Find hosts has type is 3 ( ghost host )
		 */
		boolean isGhostHostSelected = entities.stream()
				.anyMatch(e -> e.getHostType() == 3);
		
		String ghostHostName = null;
		
		if(isGhostHostSelected) {
			ghostHostName = entities.stream()
					.filter(e -> e.getHostType() == 3 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}else {
			ghostHostName = entities.stream()
					.filter(e -> e.getHostType() == 2 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}
		
		
		if(model.getGhostHostName() == null) model.setGhostHostName(ghostHostName);
		
		
		
		/*
		 * Find hosts has type is 2 ( slaves )
		 */
		List<String> checkReplicaList = entities.stream()
				.filter(e -> e.getHostType() == 2 )
				.map(q -> q.getMysqlHostName())
				.collect(Collectors.toList());
		
		if(model.getCheckReplicaList() == null) model.setCheckReplicaList((ArrayList<String>)checkReplicaList);
		
		
		
		if( masterHostName.size() == 1 ) {
			/*
			 * dryrun
			 */
			list = ghostComponent.ghostRun(model, "--verbose", model.isCutOver());
			
		}else {
			list.add("Dry run fail due to multi master");
		}
		
		return list;
		
	}
	
	
	
	public String execute(GhostModel model){
		
		logger.debug("cut over : "+model.isCutOver());
		if(model.getGhostHostName() == null) model.setGhostHostName("N/A");
		
		/*
		 * Set id
		 */
		AlterStatusEntityId id = new AlterStatusEntityId(model.getTableName(),model.getClusterName(),model.getTableSchema());
		
		
		
		/*
		 * Check alter is running already
		 */
		Optional<AlterStatusEntity> optional  = alterStatusRepository.findById(id);
		if(!optional.isEmpty()) return "Execution Error : Alter is running";
		
		
		
		/*
		 * Save to alter status table
		 */
		AlterStatusEntity entity = new AlterStatusEntity();
		
		Timestamp registerTime = new Timestamp(System.currentTimeMillis());
		
		entity.setClusterName(model.getClusterName());
		entity.setTableSchema(model.getTableSchema());
		entity.setTableName(model.getTableName());
		entity.setGhostHostName(model.getGhostHostName());
		//entity.setCheckReplicaList(model.getCheckReplicaList());
		//entity.setAlterStatement(model.getAlterStatement());
		entity.setRegisterEmail(model.getRegisterEmail());
		entity.setRegisterTimestamp(registerTime);
		
		alterStatusRepository.save(entity);
		
		/*
		 * Set MySQL host by clusterName
		 */
		List<MySQLHostEntity> entities = mySQLHostRepository.findAllMySQLHostByClusterName(model.getClusterName());
		
		/*
		 * Find hosts has type is 2 ( slaves )
		 */
		List<String> checkReplicaList = entities.stream()
				.filter(e -> e.getHostType() == 2 )
				.map(q -> q.getMysqlHostName())
				.collect(Collectors.toList());
		
		if(model.getCheckReplicaList() == null) model.setCheckReplicaList((ArrayList<String>)checkReplicaList);
		
		/*
		 * Execute
		 */
		ghostComponent.ghostRun(model, "--execute", model.isCutOver());
		
		
		/*
		 * save to alter history table
		 */
		Timestamp FinishTime = new Timestamp(System.currentTimeMillis());
		
		AlterHistEntity histEntity = new AlterHistEntity();
		
		histEntity.setClusterName(model.getClusterName());
		histEntity.setTableSchema(model.getTableSchema());
		histEntity.setTableName(model.getTableName());
		histEntity.setGhostHostName(model.getGhostHostName());
		//histEntity.setCheckReplicaList(model.getCheckReplicaList());
		//histEntity.setAlterStatement(model.getAlterStatement());
		histEntity.setRegisterEmail(model.getRegisterEmail());
		histEntity.setRegisterTimestamp(registerTime);
		histEntity.setCreateTimestamp(FinishTime);
		
		alterHistRepository.save(histEntity);
		
		/*
		 * Delete alter status table
		 */
		alterStatusRepository.deleteById(id);
		
		
		return "Alter Completed.";
		
	}
	
	
	
	
	public List<AlterStatusEntity> findAlterStatusAll() {
		return alterStatusRepository.findAll();
	}

	public List<AlterHistEntity> findAlterHistAll() {
		return alterHistRepository.findAll();
	}
	
	
	public String dropShadowTable(String clusterName, String tableSchema, String tableName) {
		
		StringBuffer rtn = new StringBuffer();
		
		/*
		 * Find master
		 */
		List<MySQLHostEntity> entities = mySQLHostRepository.findAllMySQLHostByClusterName(clusterName);
		
		List<MySQLHostEntity> filtered = entities.stream()
				.filter(e -> e.getHostType() == 1)
				.collect(Collectors.toList());
		
		if(filtered.size() != 1 ) {
			return new StringBuffer("Drop failed due to Multi master found.").toString();
		}
		
		/*
		 * Table name validation
		 */
		if(!ghostComponent.isValidateTableName(tableName)) return new StringBuffer("Drop failed due to invalid table name : "+ tableName).toString();
		
		
		/*
		 * Find target shadow table
		 */
		MySQLHostEntity entity = filtered.get(0);
		logger.info("Master host found : "+ entity.getMysqlHostName());
		rtn.append("Master host found : "+ entity.getMysqlHostName()+"\n");
		
		TableInfo tableInfo = targetMySQLRepository.findShadowTable(entity.getMysqlHostName(), 3306, tableSchema, tableName);
		if(tableInfo == null) return new StringBuffer("Target table not found : " + tableSchema+"."+tableName).toString();
		
		/*
		 * drop table
		 */
		boolean success = targetMySQLRepository.dropShadowTable(entity.getMysqlHostName(), 3306, tableSchema, tableName);
		rtn.append((success)?"Drop completed.":"Drop failed.");
		
		return rtn.toString();
		
	}



	public List<AlterCount> findAlterHistByDay(@Valid String day) {
		return entityManagerComponent.findAlterHistByDay(day);
	}
	
	
}
