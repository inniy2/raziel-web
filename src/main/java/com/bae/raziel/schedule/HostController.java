package com.bae.raziel.schedule;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HostController {
	
	
	@Autowired
	private HostService hostSevice;
	
	@PostMapping("/host/savehost")
	public HostDTO saveHost(@Valid @RequestBody HostDTO hostDTO) {
		return hostSevice.saveHost(hostDTO);
	}
	
	@PostMapping("/mysql/savemysql")
	public MySQLDTO saveMySQL(@Valid @RequestBody MySQLDTO mysqlDTO) {
		return hostSevice.saveMySQL(mysqlDTO);
	}
	
	@PostMapping("/table/savetable")
	public List<TableDTO> saveTables(@Valid @RequestBody List<TableDTO> tableDTOList) {
		return hostSevice.saveTables(tableDTOList);
	}
	
}
