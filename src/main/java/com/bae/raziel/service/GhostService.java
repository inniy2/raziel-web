package com.bae.raziel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bae.raziel.component.GhostComponent;
import com.bae.raziel.entity.GhostEntity;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.entity.MySQLHostEntity;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.repository.GhostRepository;
import com.bae.raziel.repository.MySQLHostRepository;
import com.bae.raziel.repository.TargetMySQLRepository;


@Service
public class GhostService {
	
	private static final Logger logger = LoggerFactory.getLogger(GhostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	

	@Autowired
	GhostComponent ghostComponent;
	
	
	@Autowired
	GhostRepository ghostRepository;
	
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
	
	
	@Value("${console.mysql.user}")
	private String consoleMySQLUser;

	@Value("${console.mysql.password}")
	private String consoleMySQLpassword;
	

	
	
	
	
	
	
	
	public GhostModel findGHCByProgress(GhostModel ghostDto) {
		
		MySQLEntity mySQLDao = this.mapToMySQLDao(ghostDto);
		
		
		List<MySQLHostEntity> mySQLHostEntityList = mySQLHostRepository.findAllMySQLHostByClusterName(ghostDto.getClusterName());
		
		/*
		 * Find hosts has type is 3 ( ghost host )
		 */
		boolean isGhostHostSelected = mySQLHostEntityList.stream()
				.anyMatch(e -> e.getHostType() == 3);
		
		String ghostHostName = null;
		
		if(isGhostHostSelected) {
			ghostHostName = mySQLHostEntityList.stream()
					.filter(e -> e.getHostType() == 3 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}else {
			ghostHostName = mySQLHostEntityList.stream()
					.filter(e -> e.getHostType() == 2 )
					.findFirst()
					.map(q -> q.getMysqlHostName())
					.get();
		}
		
		if( mySQLDao.getHostName() == null ) mySQLDao.setHostName(ghostHostName);
		
		try {
			
			mySQLDao = targetMySQLRepository.getGhc(mySQLDao);
			
		}catch(NullPointerException ne) {
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		GhostModel returnGhostDto = this.mapToGhostDto(mySQLDao);
		
		returnGhostDto.setClusterName(ghostDto.getClusterName());
		returnGhostDto.setTableName(ghostDto.getTableName());
		returnGhostDto.setAlterStatement(ghostDto.getAlterStatement());
		returnGhostDto.setRegisterEmail(ghostDto.getRegisterEmail());
		
		if(ghostDto.getGhostHostName() != null )returnGhostDto.setGhostHostName(ghostDto.getGhostHostName());
		if(ghostDto.getCheckReplicaList() != null )returnGhostDto.setCheckReplicaList(ghostDto.getCheckReplicaList());
		
		
		return returnGhostDto;
	}
	
	
	
	public List<GhostModel> findAll(GhostModel ghostDto){
		
		List<GhostModel> ghostDtoList = new ArrayList<GhostModel>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findAll();
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	public List<GhostModel> findAllByProgress(GhostModel ghostDto){
		
		List<GhostModel> ghostDtoList = new ArrayList<GhostModel>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findHistoryAllByProgressStatus(ghostDto.getProgressStatus());
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	
	
	public List<String> dryRun(GhostModel model){
		
		List<String> list = ghostComponent.getList();
		
		List<MySQLHostEntity> entities = mySQLHostRepository.findAllMySQLHostByClusterName(model.getClusterName());
		
		logger.debug(model.getTableSchema());
		
		if(entities.isEmpty()) {
			list.add("No hosts for : "+ model.getClusterName());
			return list;
		}else if(entities.size() == 0) {
			list.add("Hosts count is : "+ entities.size());
			return list;
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
			list = ghostComponent.ghostRun(model, "--verbose");
			
		}else {
			list.add("Dry run fail due to multi master");
		}
		
		return list;
		
	}
	
	
	
	public void execute(GhostModel model){
		
		/*
		 * dryrun
		 */
		// List<String> list = this.dryRun(model);
		
		/*
		 * Search history table to confirm alter status
		 * 0. Is it the first time?
		 * 1. Is it in progress?
		 * 2. Is it completed?
		 */
		
		/*
		 * read
		 */
		GhostEntity ghostEntity = ghostRepository.findHistoryByPrimary(model.getTableName(),model.getClusterName(),model.getTableSchema());
		
		
		
		
		/*
		 * Is it the first time?
		 */
		if(ghostEntity == null) {
			
			logger.debug("-------------------------------------------------------");
			logger.debug("The first time for alter");
			logger.debug("-------------------------------------------------------");
			
			/*
			 * create fields
			 */
			ghostEntity = this.mapToGhostEntity(model);
			ghostEntity.setProgressStatus(1);     // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(model.getTableName(),model.getClusterName(),model.getTableSchema());
			
			
			/*
			 * Alter
			 */
			ghostComponent.ghostRun(model, "--execute");
			
			/*
			 * update fields
			 */
			ghostEntity.setProgressStatus(2); // alter completed
			ghostEntity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			
			ghostRepository.save(ghostEntity);
			
			/*
			 * 1. Is it in progress?
			 */	
		}else if(ghostEntity.getProgressStatus() == 1) {  // in progress
			logger.debug("-------------------------------------------------------");
			logger.debug("Alter in progress");
			logger.debug("-------------------------------------------------------");
			
			
			logger.info(model.getClusterName() +" "+ model.getTableSchema() +" "+ model.getTableName() + " alter in progress ... ");
			
			/*
			 * 2. Is it completed?
			 */	
		}else if(ghostEntity.getProgressStatus() == 2) {  // alter completed
			logger.debug("-------------------------------------------------------");
			logger.debug("New alter has started");
			logger.debug("-------------------------------------------------------");
			
			
			/*
			 * Update fields
			 */
			ghostEntity.setAlterStatement(model.getAlterStatement());
			ghostEntity.setCheckReplicaList(model.getCheckReplicaList());
			ghostEntity.setProgressStatus(1); // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(model.getTableName(),model.getClusterName(),model.getTableSchema());
			
			
			/*
			 * Alter
			 */
			ghostComponent.ghostRun(model, "--execute");
			
			
			
			/*
			 * Update fields
			 */
			ghostEntity.setProgressStatus(2); // alter completed
			ghostEntity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
		}else {
			
			logger.error("Alter status is not defined");
			
		}
		
		
	}
	
	
	
	private GhostModel mapToGhostDto(MySQLEntity mySQLDao) {
		GhostModel ghostDto = null;
		
		if( mySQLDao != null ) {
			ghostDto = new GhostModel();
			
			ghostDto.setGhostHostName(mySQLDao.getHostName());
			ghostDto.setTableSchema(mySQLDao.getTableSchema());
			ghostDto.setTableName(mySQLDao.getTableName());
			
			ghostDto.setId(mySQLDao.getId());
			ghostDto.setLastUpdate(mySQLDao.getLastUpdate());
			ghostDto.setHint(mySQLDao.getHint());
			ghostDto.setValue(mySQLDao.getValue());
			
		}
		
		
		
		return ghostDto;
	}
	
	
	private MySQLEntity mapToMySQLDao(GhostModel ghostDto) {
		MySQLEntity mySQLDao = new MySQLEntity();
		
		mySQLDao.setHostName(ghostDto.getGhostHostName());
		mySQLDao.setUser(consoleMySQLUser);
		mySQLDao.setPassword(consoleMySQLpassword);
		mySQLDao.setPort(3306);
		mySQLDao.setTableSchema(ghostDto.getTableSchema());
		
		return mySQLDao;
	}
	
	
	private GhostModel mapToGhostDto(GhostEntity ghostEntity, int orderId) {
		
		GhostModel ghostDto = null;
		
		if( ghostEntity != null ) {
			ghostDto = new GhostModel();
			ghostDto.setOrderId(orderId);
			ghostDto.setClusterName(ghostEntity.getClusterName());
			ghostDto.setTableSchema(ghostEntity.getTableSchema());
			ghostDto.setTableName(ghostEntity.getTableName());
			ghostDto.setGhostHostName(ghostEntity.getGhostHostName());
			ghostDto.setCheckReplicaList(ghostEntity.getCheckReplicaList());
			ghostDto.setAlterStatement(ghostEntity.getAlterStatement());
			ghostDto.setRegisterEmail(ghostEntity.getRegisterEmail());
			ghostDto.setProgressStatus(ghostEntity.getProgressStatus());
			ghostDto.setCreateTimestamp(ghostEntity.getCreateTimestamp());
			ghostDto.setUpdateTimestamp(ghostEntity.getUpdateTimestamp());
		}
		
		return ghostDto;
		
	}
	
	
	private GhostEntity mapToGhostEntity(GhostModel ghostDto) {
		
		GhostEntity ghostEntity = null;

		if( ghostDto != null ) {
			ghostEntity = new GhostEntity();
			ghostEntity.setClusterName(ghostDto.getClusterName());
			ghostEntity.setTableSchema(ghostDto.getTableSchema());
			ghostEntity.setTableName(ghostDto.getTableName());
			ghostEntity.setGhostHostName(ghostDto.getGhostHostName());
			ghostEntity.setCheckReplicaList(ghostDto.getCheckReplicaList());
			ghostEntity.setAlterStatement(ghostDto.getAlterStatement());
			ghostEntity.setRegisterEmail(ghostDto.getRegisterEmail());
			ghostEntity.setProgressStatus(ghostDto.getProgressStatus());
			ghostEntity.setCreateTimestamp(ghostDto.getCreateTimestamp());
			ghostEntity.setUpdateTimestamp(ghostDto.getUpdateTimestamp());
		}
		
		
		return ghostEntity;
		
	}
	
	
	
	
	
}
