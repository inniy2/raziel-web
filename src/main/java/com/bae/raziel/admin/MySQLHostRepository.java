package com.bae.raziel.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import com.bae.raziel.ghost.GhostEntity;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface MySQLHostRepository extends JpaRepository<MySQLHostEntity, String>{
	
	
	@Transactional(readOnly = true)
	@Query("SELECT a FROM MySQLHostEntity a WHERE a.clusterName = ?1")
	List<MySQLHostEntity> findAllMySQLHostByClusterName (String clusterName);
	
}
