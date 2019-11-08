package com.bae.raziel.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.raziel.entity.LoginEntity;






@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String>{
	
	

}
