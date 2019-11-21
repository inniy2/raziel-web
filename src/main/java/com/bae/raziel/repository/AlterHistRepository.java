package com.bae.raziel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bae.raziel.entity.AlterHistEntity;


@Repository
public interface AlterHistRepository extends JpaRepository<AlterHistEntity, Long>{

}
