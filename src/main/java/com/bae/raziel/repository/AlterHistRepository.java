package com.bae.raziel.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bae.raziel.entity.AlterHistEntity;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface AlterHistRepository extends JpaRepository<AlterHistEntity, Long>{
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3")
	AlterHistEntity findHistoryByPrimary (String tableName, String clusterName, String tableSchema);
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.progressStatus = ?1")
	List<AlterHistEntity> findHistoryAllByProgressStatus (int progressStatus );
	
	
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3 and a.progressStatus = ?4")
	AlterHistEntity findHistoryByProgressStatus (String tableName, String clusterName, String tableSchema, int progressStatus );
	

	
}
