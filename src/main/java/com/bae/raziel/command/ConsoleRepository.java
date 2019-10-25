package com.bae.raziel.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class ConsoleRepository {
	

	private static final Logger logger = LoggerFactory.getLogger(ConsoleRepository.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL1
	String findClusterNameByTableNameSql = 
			"select table_name, cluster_name, count(*) as mysql_node_count " + 
			"from table_node " + 
			"where table_name = ? " + 
			"group by table_name, cluster_name";
	
	// SQL2
	String findClusterNameByTableNameClusterNameSql = 
			"select table_name, cluster_name, table_schema, count(*) as mysql_node_count " + 
			"from table_node " + 
			"where table_name = ? " +
			"and cluster_name = ? " +
			"group by table_name, cluster_name, table_schema";
	
	// SQL3
	String findClusterNameByTableNameTableSchemaSql = 
			"select table_name, cluster_name, table_schema, count(*) as mysql_node_count " + 
			"from table_node " + 
			"where table_name = ? " +
			"and table_schema = ? " +
			"group by table_name, cluster_name, table_schema";
	
	
	// SQL4
	String findTableAlterInfoSql = 
			"select tn.id, tn.host_name, tn.table_name, tn.cluster_name, tn.table_schema, tn.data_length, tn.data_timestamp as table_data_timestamp, " + 
			"hn.cpu_percentage, hn.data_timestamp as host_data_timestamp, hn.free_disk_percentage, hn.free_disk_size, hn.ghost_postpone_file, hn.ghost_sock_count, hn.ghost_version, hn.ghost_running, hn.mysql_data_size, hn.mysql_pid, hn.mysql_running, hn.mysql_sock, hn.total_disk_size, " +
			"mn.data_timestamp as mysql_data_timestamp , mn.innodb_version, mn.master_active_count, mn.master_host_name, mn.mysql_version, mn.read_only, mn.report_host_name, mn.slave_count, mn.slave_host_name, " +
			"0 as alter_time " +
			"from table_node tn inner join host_node hn  on tn.id = hn.id " +
			"inner join mysql_node mn on tn.id = mn.id "+
			"where tn.table_name = ? " +
			"and tn.cluster_name = ? " +
			"and tn.table_schema = ? " ;
	
	// SQL1
	/*
	public List<String[]> findCluster(String tableName){
		
		
		List<String[]> result = jdbcTemplate.query(findClusterNameByTableNameSql, 
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setString(1, tableName);
					}
			
				},
				new ResultSetExtractor<List<String[]>>(){  
					@Override
					 public List<String[]> extractData(ResultSet rs) throws SQLException,  DataAccessException {
						
						List<String[]> list = new ArrayList<String[]>();
						while(rs.next()){ 
							String[] result = {rs.getString("cluster_name"),rs.getString("mysql_node_count")};
							list.add(result);
						}
						
						return list;
					}
					
				});
		
		
		logger.debug("result size: "+ result.size());
		return result;
		
	}
	*/
	
	public List<ConsoleDTO> findCluster(String tableName){
		
		
		List<ConsoleDTO> result = jdbcTemplate.query(findClusterNameByTableNameSql, 
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setString(1, tableName);
					}
			
				},
				new ResultSetExtractor<List<ConsoleDTO>>(){  
					@Override
					 public List<ConsoleDTO> extractData(ResultSet rs) throws SQLException,  DataAccessException {
						
						List<ConsoleDTO> list = new ArrayList<ConsoleDTO>();
						while(rs.next()){
							ConsoleDTO consoleDTO = new ConsoleDTO();
							consoleDTO.setTableName(rs.getString("table_name"));
							consoleDTO.setClusterName(rs.getString("cluster_name"));
							consoleDTO.setMysqlNodeCount(rs.getInt("mysql_node_count"));
							list.add(consoleDTO);
						}
						
						return list;
					}
					
				});
		
		
		logger.debug("result size: "+ result.size());
		return result;
		
	}
	
	
	// SQL2
	public List<ConsoleDTO> findClusterByClusterName(String tableName, String clusterName){
		
		
		List<ConsoleDTO> result = jdbcTemplate.query(findClusterNameByTableNameClusterNameSql, 
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setString(1, tableName);
						preparedStatement.setString(2, clusterName);
					}
			
				},
				new ResultSetExtractor<List<ConsoleDTO>>(){  
					@Override
					 public List<ConsoleDTO> extractData(ResultSet rs) throws SQLException,  DataAccessException {
						
						List<ConsoleDTO> list = new ArrayList<ConsoleDTO>();
						while(rs.next()){ 
							ConsoleDTO consoleDTO = new ConsoleDTO();
							consoleDTO.setTableName(rs.getString("table_name"));
							consoleDTO.setTableSchema(rs.getString("table_schema"));
							consoleDTO.setClusterName(rs.getString("cluster_name"));
							consoleDTO.setMysqlNodeCount(rs.getInt("mysql_node_count"));
							list.add(consoleDTO);
						}
						
						return list;
					}
					
				});
		
		
		logger.debug("result size: "+ result.size());
		return result;
		
	}
	
	
	// SQL3
		public List<ConsoleDTO> findClusterByTableSchema(String tableName, String tableSchema){
			
			
			List<ConsoleDTO> result = jdbcTemplate.query(findClusterNameByTableNameTableSchemaSql, 
					new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement preparedStatement) throws SQLException {
							preparedStatement.setString(1, tableName);
							preparedStatement.setString(2, tableSchema);
						}
				
					},
					new ResultSetExtractor<List<ConsoleDTO>>(){  
						@Override
						 public List<ConsoleDTO> extractData(ResultSet rs) throws SQLException,  DataAccessException {
							
							List<ConsoleDTO> list = new ArrayList<ConsoleDTO>();
							while(rs.next()){ 
								ConsoleDTO consoleDTO = new ConsoleDTO();
								consoleDTO.setTableName(rs.getString("table_name"));
								consoleDTO.setTableSchema(rs.getString("table_schema"));
								consoleDTO.setClusterName(rs.getString("cluster_name"));
								consoleDTO.setMysqlNodeCount(rs.getInt("mysql_node_count"));
								list.add(consoleDTO);
							}
							
							return list;
						}
						
					});
			
			
			logger.debug("result size: "+ result.size());
			return result;
			
		}
	
	
	
	
	// SQL4
	public List<ConsoleDTO> findTableAlterInfo(String tableName, String clusterName, String tableSchema){
		
		
		List<ConsoleDTO> result = jdbcTemplate.query(findTableAlterInfoSql, 
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setString(1, tableName);
						preparedStatement.setString(2, clusterName);
						preparedStatement.setString(3, tableSchema);
					}
			
				},
				new ResultSetExtractor<List<ConsoleDTO>>(){  
					@Override
					 public List<ConsoleDTO> extractData(ResultSet rs) throws SQLException,  DataAccessException {
						
						List<ConsoleDTO> list = new ArrayList<ConsoleDTO>();
						while(rs.next()){ 
							ConsoleDTO consoleDTO = new ConsoleDTO();
							consoleDTO.setId(rs.getLong("id"));
							consoleDTO.setHostName(rs.getString("host_name"));
							consoleDTO.setTableName(rs.getString("table_name"));
							consoleDTO.setTableSchema(rs.getString("table_schema"));
							consoleDTO.setClusterName(rs.getString("cluster_name"));
							consoleDTO.setDataLength(rs.getLong("data_length"));
							consoleDTO.setTableDataTimestamp(rs.getTimestamp("table_data_timestamp"));
													
							
							consoleDTO.setCpuPercentage(rs.getFloat("cpu_percentage"));
							consoleDTO.setHostDataTimestamp(rs.getTimestamp("host_data_timestamp"));
							consoleDTO.setFreeDiskPercentage(rs.getFloat("free_disk_percentage"));
							consoleDTO.setFreeDiskSize(rs.getLong("free_disk_size"));
							consoleDTO.setGhostPostponeFile(rs.getBoolean("ghost_postpone_file"));
							consoleDTO.setGhostSockCount(rs.getInt("ghost_sock_count"));
							consoleDTO.setGhostVersion(rs.getString("ghost_version"));
							consoleDTO.setGhostRunning(rs.getBoolean("ghost_running"));
							consoleDTO.setMysqlDataSize(rs.getLong("mysql_data_size"));
							consoleDTO.setMysqlPid(rs.getBoolean("mysql_pid"));
							consoleDTO.setMysqlRunning(rs.getBoolean("mysql_running"));
							consoleDTO.setMysqlSock(rs.getBoolean("mysql_sock"));
							consoleDTO.setTotalDiskSize(rs.getLong("total_disk_size"));
							
							
							consoleDTO.setMysqlDataTimestamp(rs.getTimestamp("mysql_data_timestamp"));
							consoleDTO.setInnodbVersion(rs.getString("innodb_version"));
							consoleDTO.setMasterActiveCount(rs.getInt("master_active_count"));
							consoleDTO.setMasterHostName(rs.getString("master_host_name"));
							consoleDTO.setMysqlVersion(rs.getString("mysql_version"));
							consoleDTO.setReadOnly(rs.getBoolean("read_only"));
							consoleDTO.setReportHostName(rs.getString("report_host_name"));
							consoleDTO.setSlaveCount(rs.getInt("slave_count"));
							consoleDTO.setSlaveHostName(rs.getString("slave_host_name"));
							
							
							consoleDTO.setAlterTime(rs.getInt("alter_time"));
							
							list.add(consoleDTO);
						}
						
						return list;
					}
					
				});
		
		
		logger.debug("result size: "+ result.size());
		return result;
		
	}
}
