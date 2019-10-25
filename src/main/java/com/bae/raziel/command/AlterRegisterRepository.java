package com.bae.raziel.command;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface AlterRegisterRepository extends JpaRepository<AlterRegisterDTO, Long>{
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM AlterRegisterDTO a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3")
	List<AlterRegisterDTO> findIdByPrimary (String tableName, String clusterName, String tableSchema);
	
}
