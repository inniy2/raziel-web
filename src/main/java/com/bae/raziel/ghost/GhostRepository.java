package com.bae.raziel.ghost;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface GhostRepository extends JpaRepository<GhostEntity, Long>{
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3")
	GhostEntity findHistoryByPrimary (String tableName, String clusterName, String tableSchema);
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.progressStatus = ?1")
	List<GhostEntity> findHistoryAllByProgressStatus (int progressStatus );
	
	
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM GhostEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3 and a.progressStatus = ?4")
	GhostEntity findHistoryByProgressStatus (String tableName, String clusterName, String tableSchema, int progressStatus );
	

	
}
