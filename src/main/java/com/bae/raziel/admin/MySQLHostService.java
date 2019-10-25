package com.bae.raziel.admin;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bae.raziel.mysql.MySQLDao;
import com.bae.raziel.mysql.TargetMySQLRepository;

@Service
public class MySQLHostService {

	private static final Logger logger = LoggerFactory.getLogger(MySQLHostService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	
	@Autowired
	MySQLHostRepository mySQLHostRepository;
	
	
	
	
	
	/*
	 * (Read target table info from production mysql)
	 * Read target mysql variables: read-only
	 */
	@Autowired
	TargetMySQLRepository targetMySQLRepository;
	
	@Value("${console.mysql.user}")
	private String consoleMySQLUser;

	@Value("${console.mysql.password}")
	private String consoleMySQLpassword;
		
	
	
	
	public List<MySQLHostDto> updateGhostHost(MySQLHostDto mySQLHostDto){
		
		
		/*
		 * save ghost host
		 */
		mySQLHostRepository.save(this.mapToMySQLHostEntity(mySQLHostDto));
		
		
		/*
		 * read
		 */
		List<MySQLHostEntity> mySQLHostEntityList = mySQLHostRepository.findAllMySQLHostByClusterName(mySQLHostDto.getClusterName());
		
		List<MySQLHostDto> MySQLHostDto = new ArrayList<MySQLHostDto>();
		
		mySQLHostEntityList.forEach(e -> MySQLHostDto.add(this.mapToMySQLHostDto(e) ));
		
		return MySQLHostDto;
	}
	
	
	public List<MySQLHostDto> updateReadOnly(MySQLHostDto mySQLHostDto){
		
		/*
		 * Get all host by cluster name
		 */
		List<MySQLHostEntity>  mySQLHostEntityList = mySQLHostRepository.findAllMySQLHostByClusterName(mySQLHostDto.getClusterName());
		
		List<MySQLDao> mySQLDaoList = new ArrayList<MySQLDao>();
		
		mySQLHostEntityList.forEach(e -> mySQLDaoList.add(this.mapToMySQLDao(e) ));
		
		
		/*
		 * read read_only 
		 */
		List<MySQLDao> resultMySQLDaoList = new ArrayList<MySQLDao>();
		mySQLDaoList.forEach(e -> resultMySQLDaoList.add(targetMySQLRepository.getReadOnly(e)));
		
		/*
		 * Save 
		 */
		List<MySQLHostDto> mySQLHostDtoList = new ArrayList<MySQLHostDto>();
		resultMySQLDaoList.forEach(e -> mySQLHostDtoList.add(this.mapToMySQLHostDto(e, mySQLHostDto.getClusterName())));
		this.save(mySQLHostDtoList);

		
		
		return mySQLHostDtoList;
	}
	
	
	
	
	
	public List<MySQLHostDto> save(List<MySQLHostDto> mySQLHostDtoList) {
		
		List<MySQLHostEntity>  mySQLHostEntityList  = new ArrayList<MySQLHostEntity>();
		
		
		mySQLHostDtoList.forEach(e -> mySQLHostEntityList.add(this.mapToMySQLHostEntity(e)) );
		
		
		mySQLHostRepository.saveAll(mySQLHostEntityList);
		
		
		return mySQLHostDtoList;
	}
	
	
	
	/*
	 * for updateGhostHost
	 */
	private MySQLHostDto mapToMySQLHostDto(MySQLHostEntity mySQLHostEntity) {
		
		MySQLHostDto mySQLHostDto = new MySQLHostDto();
		
		mySQLHostDto.setClusterName(mySQLHostEntity.getClusterName());
		mySQLHostDto.setHostName(mySQLHostEntity.getMysqlHostName());
		mySQLHostDto.setHostType(mySQLHostEntity.getHostType());
		
		return mySQLHostDto;
	}
	
	
	
	/*
	 * for updateReadOnly
	 */
	private MySQLHostDto mapToMySQLHostDto(MySQLDao mySQLDao, String clusterName) {
		
		MySQLHostDto mySQLHostDto = new MySQLHostDto();
		
		mySQLHostDto.setClusterName(clusterName);
		mySQLHostDto.setHostName(mySQLDao.getHostName());
		
		if(mySQLDao.getReadOnly().equals("OFF")) {
			mySQLHostDto.setHostType(1);
		}else if(mySQLDao.getReadOnly().equals("ON")) {
			mySQLHostDto.setHostType(2);
		}else {
			mySQLHostDto.setHostType(0);
		}
		
		return mySQLHostDto;
		
	}
	
	
	/*
	 * for updateReadOnly
	 */
	private MySQLDao mapToMySQLDao(MySQLHostEntity mySQLHostEntity) {
		
		MySQLDao mySQLDao = new MySQLDao();
		
		mySQLDao.setHostName(mySQLHostEntity.getMysqlHostName());
		mySQLDao.setUser(consoleMySQLUser);
		mySQLDao.setPassword(consoleMySQLpassword);
		mySQLDao.setPort(3306);
		
		return mySQLDao;
		
	}
	
	
	
	
	/*
	 * for save
	 */
	
	private MySQLHostEntity mapToMySQLHostEntity (MySQLHostDto mySQLHostDto) {
		MySQLHostEntity mySQLHostEntity = new MySQLHostEntity();
		
		mySQLHostEntity.setClusterName(mySQLHostDto.getClusterName());
		mySQLHostEntity.setMysqlHostName(mySQLHostDto.getHostName());
		mySQLHostEntity.setHostType(mySQLHostDto.getHostType());
		
		return mySQLHostEntity;
	} 
	
	
}
