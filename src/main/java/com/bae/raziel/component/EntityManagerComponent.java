package com.bae.raziel.component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bae.raziel.domain.AlterCount;

@Component
public class EntityManagerComponent {
	private static final Logger logger = LoggerFactory.getLogger(EntityManagerComponent.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@PersistenceContext
	EntityManager entityManager;
	
	
	public List<AlterCount> findAlterHistByDay(@Valid String day) {
		
		List<AlterCount> alterCounts = new ArrayList<AlterCount>();
		
		/*
		 * Use entity class name and variables
		 */
		String sql = 
				  "SELECT SUBSTRING( a.registerTimestamp, 1,10)  as date, "
				+ "count(*) AS count "
				+ "FROM AlterHistEntity a  "
				+ "WHERE a.registerTimestamp >= DATEADD('DAY',"+day+", CURRENT_DATE) "
				+ "GROUP BY SUBSTRING(a.registerTimestamp, 1,10)";
		Query query = entityManager.createQuery(sql);
		List<Object[]> rtn = query.getResultList();
		rtn.forEach(e -> { 
			   AlterCount alterCount = new AlterCount();
				List<Object> list = Arrays.asList(e);				
				alterCount.setDate(list.get(0).toString());
				alterCount.setCount(Integer.parseInt(list.get(1).toString()));
				alterCounts.add(alterCount);
			});
		entityManager.close();
		
		return alterCounts;
	}
	
	
	
}
