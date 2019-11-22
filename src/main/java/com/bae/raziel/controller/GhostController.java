package com.bae.raziel.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bae.raziel.domain.AlterCount;
import com.bae.raziel.entity.AlterHistEntity;
import com.bae.raziel.entity.AlterStatusEntity;
import com.bae.raziel.entity.MySQLEntity;
import com.bae.raziel.model.GhcModel;
import com.bae.raziel.model.GhostModel;
import com.bae.raziel.model.GraphOption;
import com.bae.raziel.model.GraphOption.Data;
import com.bae.raziel.model.GraphOption.DataPoint;
import com.bae.raziel.service.GhostService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ghost")
public class GhostController {
	private static final Logger logger = LoggerFactory.getLogger(GhostController.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	GhostService ghostService;
	
	
	/*
	 * requirement
	 * clusterName
	 * ghostHostName
	 * checkReplicaList
	 * tableName
	 * tableSchema
	 * alterStatement
	 * cutOver
	 */
	@PostMapping("/dryrun")
	public List<String> dryRun(@Valid @RequestBody GhostModel model){
		return ghostService.dryRun(model);
	}
	
	
	/*
	 * requirement
	 * clusterName
	 * ghostHostName
	 * checkReplicaList
	 * tableName
	 * TableSchema
	 * cutOver
	 * registerEmail
	 */
	@PostMapping("/execute")
	public String execute(@Valid @RequestBody GhostModel model){
		logger.debug(model.getGhostHostName());
		return ghostService.execute(model);
	}
	

	@GetMapping("/findAlterStatusAll")
	public List<AlterStatusEntity> findAlterStatusAll(){
		return ghostService.findAlterStatusAll();
	}
	
	@GetMapping("/findAlterHistAll")
	public List<AlterHistEntity> findAlterHistAll(){
		return ghostService.findAlterHistAll();
	}
	
	@GetMapping("/findAlterHistByDay")
	public List<AlterCount> findAlterHistByDay(@Valid @RequestParam String day){
		return ghostService.findAlterHistByDay(day);
	}
	
	
	@GetMapping("/findAlterHist")
	public GraphOption findAlterHistByDay() throws ParseException{

		GraphOption graphOption = new GraphOption();
		
		Data data = (Data) graphOption.getData().get(0);
		List<DataPoint> dataPoints = data.getDataPoints();
		
		DataPoint point1 = graphOption.getNewDataPoint();
		DataPoint point2 = graphOption.getNewDataPoint();
		DataPoint point3 = graphOption.getNewDataPoint();
		DataPoint point4 = graphOption.getNewDataPoint();
		DataPoint point5 = graphOption.getNewDataPoint();
		DataPoint point6 = graphOption.getNewDataPoint();
		
		LocalDateTime myDateObj1 = LocalDateTime.of(2019,Month.JANUARY,1,0,0,0,0);
		String date1 = myDateObj1.format(dateFormatter);
		
		LocalDateTime myDateObj2 = LocalDateTime.of(2019,Month.JANUARY,2,0,0,0,0);
		String date2 = myDateObj2.format(dateFormatter);
		
		LocalDateTime myDateObj3 = LocalDateTime.of(2019,Month.JANUARY,3,0,0,0,0);
		String date3 = myDateObj3.format(dateFormatter);
		
		LocalDateTime myDateObj4 = LocalDateTime.of(2019,Month.JANUARY,4,0,0,0,0);
		String date4 = myDateObj4.format(dateFormatter);
		
		LocalDateTime myDateObj5 = LocalDateTime.of(2019,Month.JANUARY,5,0,0,0,0);
		String date5 = myDateObj5.format(dateFormatter);
		
		LocalDateTime myDateObj6 = LocalDateTime.of(2019,Month.JANUARY,6,0,0,0,0);
		String date6 = myDateObj6.format(dateFormatter);
		
		point1.setX(date1);
		point1.setY(1);
		point2.setX(date2);
		point2.setY(2);
		point3.setX(date3);
		point3.setY(3);
		point4.setX(date4);
		point4.setY(4);
		point5.setX(date5);
		point5.setY(5);
		point6.setX(date6);
		point6.setY(6);
		
		dataPoints.add(point1);
		dataPoints.add(point2);
		dataPoints.add(point3);
		dataPoints.add(point4);
		dataPoints.add(point5);
		dataPoints.add(point6);
		
		
		return graphOption;
	}
	
	
	@PostMapping("/dropShadowTable")
	public String dropShadowTable(@Valid @RequestBody GhostModel model) {
		String clusterName = model.getClusterName();
		String tableSchema = model.getTableSchema();
		String tableName = model.getTableName();
		return ghostService.dropShadowTable(clusterName, tableSchema, tableName);
	}
	
	@PostMapping("/progress")
	public GhcModel progress(@Valid @RequestBody GhostModel model) {
		String clusterName = model.getClusterName();
		String tableSchema = model.getTableSchema();
		String tableName = model.getTableName();
		return ghostService.progress(clusterName, tableSchema, tableName);
	}
	
	@PostMapping("/checkErrors")
	public List<GhcModel> checkErrors(@Valid @RequestBody GhostModel model) {
		String clusterName = model.getClusterName();
		String tableSchema = model.getTableSchema();
		String tableName = model.getTableName();
		return ghostService.checkErrors(clusterName, tableSchema, tableName);
	}
	
	
	
	/*
	@PostMapping("/findAll")
	public List<GhostModel> findAll(@Valid @RequestBody GhostModel model){
		return ghostService.findAll(model);
	}
	

	@PostMapping("/findAllByProgress")
	public List<GhostModel> findAllByProgress(@Valid @RequestBody GhostModel model){
		return ghostService.findAllByProgress(model);
	}
	
	@PostMapping("/findGHCByProgress")
	public GhostModel findGHCByProgress(@Valid @RequestBody GhostModel model){
		return ghostService.findGHCByProgress(model);
	}
	*/
	
}
