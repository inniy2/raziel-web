package com.bae.raziel.count;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;





@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/alterCount")
public class AlterCountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlterCountController.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	AlterCountService alterCountService;
	
	@PostMapping("/test")
	public AlterCountEntity dryRun(@Valid @RequestBody AlterCountEntity alterCountEntity){
		return alterCountService.test(alterCountEntity);
	}
	
}


@Service
class AlterCountService {
	
	@Autowired
	AlterCountRepository alterCountRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AlterCountService.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public AlterCountEntity test(AlterCountEntity alterCountEntity) {
		
		alterCountRepository.save(alterCountEntity);
		
		Optional<AlterCountEntity> alterCountEntityOptional = alterCountRepository.findById(1L);
		
		AlterCountEntity rtn = alterCountEntityOptional.get();
		
		rtn.setTestMessage("Miyung!");
		
		return rtn;
	}
	
}


@Component
class AlterCountComponent {
	private static final Logger logger = LoggerFactory.getLogger(AlterCountComponent.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
}


@Repository
interface AlterCountRepository extends JpaRepository<AlterCountEntity, Long>{
	
}



@Entity
//@IdClass(AlterCountEntityId.class)
@Table(name = "alter_count")
class AlterCountEntity {
	
	@Id
	@Column(name = "test_id", nullable = false)
	private long testId;

	@Transient 
	private String testMessage;
	
	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getTestMessage() {
		return testMessage;
	}

	public void setTestMessage(String testMessage) {
		this.testMessage = testMessage;
	}
	
	
	
}





