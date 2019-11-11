package com.bae.raziel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bae.raziel.entity.AlterStatusEntity;
import com.bae.raziel.multipk.AlterStatusEntityId;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface AlterStatusRepository extends JpaRepository<AlterStatusEntity, AlterStatusEntityId>{
	
	/*
	@Transactional(readOnly = true)
	@Query("SELECT a FROM AlterStatusEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3")
	AlterStatusEntity findById (String tableName, String clusterName, String tableSchema);
	
	
	
	@Query("DELETE FROM AlterStatusEntity a WHERE a.tableName = ?1 and a.clusterName = ?2 and a.tableSchema = ?3")
	void deleteById (String tableName, String clusterName, String tableSchema);
	*/
	
}
