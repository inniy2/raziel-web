package com.bae.raziel.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.raziel.entity.RazielUserEntity;






@Repository
public interface RazielUserRepository extends JpaRepository<RazielUserEntity, String>{
	
	

}
