package com.bae.raziel.ghost;

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

import com.bae.raziel.admin.MySQLHostEntity;
import com.bae.raziel.admin.MySQLHostRepository;
import com.bae.raziel.mysql.MySQLDao;
import com.bae.raziel.mysql.TargetMySQLRepository;

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
	

	
	
	String[] ghostRunComand = new String[] {
			"gh-ost",                                    // 0
			"--max-load=Threads_running=50",             // 1
			"--critical-load=Threads_running=1500",      // 2
			"--chunk-size=500",                          // 3
			"--max-lag-millis=1500",                     // 4
			"--conf=debian.cnf",                         // 5
			"--host=",                                   // 6
			"--throttle-control-replicas=",              // 7
			"--database=",                               // 8
			"--table=",                                  // 9
			"--alter=",                                  // 10
			"--switch-to-rbr",                           // 11
			"--cut-over=default",                        // 12
			"--exact-rowcount",                          // 13
			"--concurrent-rowcount",                     // 14
			"--default-retries=120",                     // 15
			"--timestamp-old-table",                     // 16
			""                                           // 17
	};
	
	
	
	
	public GhostDto findGHCByProgress(GhostDto ghostDto) {
		
		MySQLDao mySQLDao = this.mapToMySQLDao(ghostDto);
		
		
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
		
		
		GhostDto returnGhostDto = this.mapToGhostDto(mySQLDao);
		
		returnGhostDto.setClusterName(ghostDto.getClusterName());
		returnGhostDto.setTableName(ghostDto.getTableName());
		returnGhostDto.setAlterStatement(ghostDto.getAlterStatement());
		returnGhostDto.setRegisterEmail(ghostDto.getRegisterEmail());
		
		if(ghostDto.getGhostHostName() != null )returnGhostDto.setGhostHostName(ghostDto.getGhostHostName());
		if(ghostDto.getCheckReplicaList() != null )returnGhostDto.setCheckReplicaList(ghostDto.getCheckReplicaList());
		
		
		return returnGhostDto;
	}
	
	
	
	public List<GhostDto> findAll(GhostDto ghostDto){
		
		List<GhostDto> ghostDtoList = new ArrayList<GhostDto>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findAll();
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	public List<GhostDto> findAllByProgress(GhostDto ghostDto){
		
		List<GhostDto> ghostDtoList = new ArrayList<GhostDto>();
		
		List<GhostEntity> ghostEntityList = ghostRepository.findHistoryAllByProgressStatus(ghostDto.getProgressStatus());
		
		ghostEntityList.forEach(e -> ghostDtoList.add(this.mapToGhostDto(e, ghostEntityList.indexOf(e))));
		
		return ghostDtoList;
		
	}
	
	
	
	
	public GhostDto dryRun(GhostDto ghostDto){
		
		
		List<MySQLHostEntity> mySQLHostEntityList = mySQLHostRepository.findAllMySQLHostByClusterName(ghostDto.getClusterName());
		
		/*
		 * Find hosts which type is 1 ( master )
		 */
		List<String> masterHostName = mySQLHostEntityList.stream()
				.filter(e -> e.getHostType() == 1 )
				.map(q -> q.getMysqlHostName())
				.collect(Collectors.toList());
		
		logger.debug("----------------------------------- list stream master host debug -------------------------------");
		masterHostName.forEach(e -> logger.debug(e.toString()));
		logger.debug("----------------------------------- list stream master host debug -------------------------------");
		
		
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
		
		if(ghostDto.getGhostHostName() == null) ghostDto.setGhostHostName(ghostHostName);
		
		logger.debug("----------------------------------- list stream ghost host debug -------------------------------");
		logger.debug(ghostHostName);
		logger.debug("----------------------------------- list stream ghost host debug -------------------------------");
		
		
		/*
		 * Find hosts has type is 2 ( slaves )
		 */
		List<String> checkReplicaList = mySQLHostEntityList.stream()
				.filter(e -> e.getHostType() == 2 )
				.map(q -> q.getMysqlHostName())
				.collect(Collectors.toList());
		
		if(ghostDto.getCheckReplicaList() == null) ghostDto.setCheckReplicaList((ArrayList<String>)checkReplicaList);
		
		logger.debug("----------------------------------- list stream slave debug -------------------------------");
		checkReplicaList.forEach(e -> logger.debug(e.toString()));
		logger.debug("----------------------------------- list stream slave debug -------------------------------");
		
		
		
		
		if( masterHostName.size() == 1 ) {
			/*
			 * dryrun
			 */
			ghostDto.setOutputStrList(this.ghostRun(ghostDto, "--verbose"));
			
		}else {
			List<String> messages = new ArrayList<String>();
			messages.add("Dry run fail due to multi master");
			ghostDto.setOutputStrList(messages);
		}
		
		return ghostDto;
		
	}
	
	
	
	public void execute(GhostDto ghostDto){
		
		/*
		 * dryrun
		 */
		ghostDto = this.dryRun(ghostDto);
		
		
		
		GhostEntity ghostEntity = null;
		
		
		/*
		 * Search history table to confirm alter status
		 * 0. Is it the first time?
		 * 1. Is it in progress?
		 * 2. Is it completed?
		 */
		
		/*
		 * read
		 */
		ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
		
		
		
		
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
			ghostEntity = this.mapToGhostEntity(ghostDto);
			ghostEntity.setProgressStatus(1);     // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
			
			
			/*
			 * Alter
			 */
			this.ghostRun(ghostDto, "--execute");
			
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
			
			
			logger.info(ghostDto.getClusterName() +" "+ ghostDto.getTableSchema() +" "+ ghostDto.getTableName() + " alter in progress ... ");
			
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
			ghostEntity.setAlterStatement(ghostDto.getAlterStatement());
			ghostEntity.setCheckReplicaList(ghostDto.getCheckReplicaList());
			ghostEntity.setProgressStatus(1); // in progress
			ghostEntity.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			ghostRepository.save(ghostEntity);
			
			
			/*
			 * read
			 */
			ghostEntity = ghostRepository.findHistoryByPrimary(ghostDto.getTableName(),ghostDto.getClusterName(),ghostDto.getTableSchema());
			
			
			/*
			 * Alter
			 */
			this.ghostRun(ghostDto, "--execute");
			
			
			
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
	
	
	private GhostDto mapToGhostDto(MySQLDao mySQLDao) {
		GhostDto ghostDto = null;
		
		if( mySQLDao != null ) {
			ghostDto = new GhostDto();
			
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
	
	
	private MySQLDao mapToMySQLDao(GhostDto ghostDto) {
		MySQLDao mySQLDao = new MySQLDao();
		
		mySQLDao.setHostName(ghostDto.getGhostHostName());
		mySQLDao.setUser(consoleMySQLUser);
		mySQLDao.setPassword(consoleMySQLpassword);
		mySQLDao.setPort(3306);
		mySQLDao.setTableSchema(ghostDto.getTableSchema());
		
		return mySQLDao;
	}
	
	
	private GhostDto mapToGhostDto(GhostEntity ghostEntity, int orderId) {
		
		GhostDto ghostDto = null;
		
		if( ghostEntity != null ) {
			ghostDto = new GhostDto();
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
	
	
	private GhostEntity mapToGhostEntity(GhostDto ghostDto) {
		
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
	
	
	private List<String> ghostRun(GhostDto ghostDto, String verbose) {
		
		StringBuilder database      = new StringBuilder("--database=");
		StringBuilder host          = new StringBuilder("--host=");
		StringBuilder table         = new StringBuilder("--table=");
		StringBuilder alterStatment = new StringBuilder("--alter=");
		StringBuilder checkReplica  = new StringBuilder("--throttle-control-replicas=");
		
		
		logger.debug("cmd option length: "+ghostRunComand.length);
		
		/*
		 * database
		 */
		database.append(ghostDto.getTableSchema());

		
		/*
		 * host
		 */
		host.append(ghostDto.getGhostHostName());
		
		/*
		 * table
		 */
		table.append(ghostDto.getTableName());
		
		/*
		 * checkReplica
		 */
		ArrayList<String> checkReplicaList = ghostDto.getCheckReplicaList();
		for(int i = 0; i < checkReplicaList.size(); i++) {			
			if (i != 0) checkReplica.append(",");
			checkReplica.append(checkReplicaList.get(i));
		}
		
		
		/*
		 * alterStatment
		 */
		ArrayList<String> alterStatementList = ghostDto.getAlterStatement();
		for(int i = 0; i < alterStatementList.size(); i++) {			
			if (i != 0) alterStatment.append(",");
			alterStatment.append(alterStatementList.get(i));
		}
		
		
		
		for(int i= 0; i < ghostRunComand.length; i++) {
			if(i == 6) {        ghostRunComand[i] = host.toString();
			}else if (i == 8) { ghostRunComand[i] = database.toString();
			}else if (i == 9) { ghostRunComand[i] = table.toString();
			}else if (i == 7) { ghostRunComand[i] = checkReplica.toString();
			}else if (i == 10){ ghostRunComand[i] = alterStatment.toString();
			}else if (i == 17){ ghostRunComand[i] = verbose;
			}
		
			logger.info(ghostRunComand[i]);
		}
		
		
		return ghostComponent.runProcess(ghostRunComand);
		
	}
	
	
}
