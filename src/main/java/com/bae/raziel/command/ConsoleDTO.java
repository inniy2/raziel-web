package com.bae.raziel.command;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

public class ConsoleDTO {
	
	// Same as TableDTO
	private long id;
	
	private String hostName;
	
	private String tableName;
	
	private String tableSchema;

	private String clusterName;
	
	private long dataLength;
	
	private Timestamp tableDataTimestamp;
	
	private int mysqlNodeCount;

	private int alterTime;
	
	// Same as HostDTO

	
	private float cpuPercentage;
	
	private float freeDiskPercentage;
	
	private long totalDiskSize;
	
	private long freeDiskSize;
	
	private long mysqlDataSize;
	
	private String ghostVersion;
	
	private int ghostSockCount;
	
	private boolean ghostPostponeFile;
	
	private boolean ghostRunning;
	
	private boolean mysqlPid;
	
	private boolean mysqlRunning;
	
	private boolean mysqlSock;

	private Timestamp hostDataTimestamp;
	
	// Same as MySQLDTO
	
	
	private String reportHostName;
	
	private String mysqlVersion;
	
	private String innodbVersion;
	
	private Boolean readOnly;
	
	private int masterActiveCount;
	
	private int slaveCount;
	
	private String slaveHostName;
	
	private String masterHostName;

	private Timestamp mysqlDataTimestamp;
	
	
	public ConsoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getTableSchema() {
		return tableSchema;
	}


	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}


	public String getClusterName() {
		return clusterName;
	}


	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}


	public long getDataLength() {
		return dataLength;
	}


	public void setDataLength(long dataLength) {
		this.dataLength = dataLength;
	}


	public Timestamp getTableDataTimestamp() {
		return tableDataTimestamp;
	}


	public void setTableDataTimestamp(Timestamp tableDataTimestamp) {
		this.tableDataTimestamp = tableDataTimestamp;
	}


	public int getMysqlNodeCount() {
		return mysqlNodeCount;
	}


	public void setMysqlNodeCount(int mysqlNodeCount) {
		this.mysqlNodeCount = mysqlNodeCount;
	}


	public int getAlterTime() {
		return alterTime;
	}


	public void setAlterTime(int alterTime) {
		this.alterTime = alterTime;
	}


	public float getCpuPercentage() {
		return cpuPercentage;
	}


	public void setCpuPercentage(float cpuPercentage) {
		this.cpuPercentage = cpuPercentage;
	}


	public float getFreeDiskPercentage() {
		return freeDiskPercentage;
	}


	public void setFreeDiskPercentage(float freeDiskPercentage) {
		this.freeDiskPercentage = freeDiskPercentage;
	}


	public long getTotalDiskSize() {
		return totalDiskSize;
	}


	public void setTotalDiskSize(long totalDiskSize) {
		this.totalDiskSize = totalDiskSize;
	}


	public long getFreeDiskSize() {
		return freeDiskSize;
	}


	public void setFreeDiskSize(long freeDiskSize) {
		this.freeDiskSize = freeDiskSize;
	}


	public long getMysqlDataSize() {
		return mysqlDataSize;
	}


	public void setMysqlDataSize(long mysqlDataSize) {
		this.mysqlDataSize = mysqlDataSize;
	}


	public String getGhostVersion() {
		return ghostVersion;
	}


	public void setGhostVersion(String ghostVersion) {
		this.ghostVersion = ghostVersion;
	}


	public int getGhostSockCount() {
		return ghostSockCount;
	}


	public void setGhostSockCount(int ghostSockCount) {
		this.ghostSockCount = ghostSockCount;
	}


	public boolean isGhostPostponeFile() {
		return ghostPostponeFile;
	}


	public void setGhostPostponeFile(boolean ghostPostponeFile) {
		this.ghostPostponeFile = ghostPostponeFile;
	}


	public boolean isGhostRunning() {
		return ghostRunning;
	}


	public void setGhostRunning(boolean ghostRunning) {
		this.ghostRunning = ghostRunning;
	}


	public boolean isMysqlPid() {
		return mysqlPid;
	}


	public void setMysqlPid(boolean mysqlPid) {
		this.mysqlPid = mysqlPid;
	}


	public boolean isMysqlRunning() {
		return mysqlRunning;
	}


	public void setMysqlRunning(boolean mysqlRunning) {
		this.mysqlRunning = mysqlRunning;
	}


	public boolean isMysqlSock() {
		return mysqlSock;
	}


	public void setMysqlSock(boolean mysqlSock) {
		this.mysqlSock = mysqlSock;
	}


	public Timestamp getHostDataTimestamp() {
		return hostDataTimestamp;
	}


	public void setHostDataTimestamp(Timestamp hostDataTimestamp) {
		this.hostDataTimestamp = hostDataTimestamp;
	}


	public String getReportHostName() {
		return reportHostName;
	}


	public void setReportHostName(String reportHostName) {
		this.reportHostName = reportHostName;
	}


	public String getMysqlVersion() {
		return mysqlVersion;
	}


	public void setMysqlVersion(String mysqlVersion) {
		this.mysqlVersion = mysqlVersion;
	}


	public String getInnodbVersion() {
		return innodbVersion;
	}


	public void setInnodbVersion(String innodbVersion) {
		this.innodbVersion = innodbVersion;
	}


	public Boolean getReadOnly() {
		return readOnly;
	}


	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}


	public int getMasterActiveCount() {
		return masterActiveCount;
	}


	public void setMasterActiveCount(int masterActiveCount) {
		this.masterActiveCount = masterActiveCount;
	}


	public int getSlaveCount() {
		return slaveCount;
	}


	public void setSlaveCount(int slaveCount) {
		this.slaveCount = slaveCount;
	}


	public String getSlaveHostName() {
		return slaveHostName;
	}


	public void setSlaveHostName(String slaveHostName) {
		this.slaveHostName = slaveHostName;
	}


	public String getMasterHostName() {
		return masterHostName;
	}


	public void setMasterHostName(String masterHostName) {
		this.masterHostName = masterHostName;
	}


	public Timestamp getMysqlDataTimestamp() {
		return mysqlDataTimestamp;
	}


	public void setMysqlDataTimestamp(Timestamp mysqlDataTimestamp) {
		this.mysqlDataTimestamp = mysqlDataTimestamp;
	}


	@Override
	public String toString() {
		return "ConsoleDTO [id=" + id + ", hostName=" + hostName + ", tableName=" + tableName + ", tableSchema="
				+ tableSchema + ", clusterName=" + clusterName + ", dataLength=" + dataLength + ", tableDataTimestamp="
				+ tableDataTimestamp + ", mysqlNodeCount=" + mysqlNodeCount + ", alterTime=" + alterTime
				+ ", cpuPercentage=" + cpuPercentage + ", freeDiskPercentage=" + freeDiskPercentage + ", totalDiskSize="
				+ totalDiskSize + ", freeDiskSize=" + freeDiskSize + ", mysqlDataSize=" + mysqlDataSize
				+ ", ghostVersion=" + ghostVersion + ", ghostSockCount=" + ghostSockCount + ", ghostPostponeFile="
				+ ghostPostponeFile + ", ghostRunning=" + ghostRunning + ", mysqlPid=" + mysqlPid + ", mysqlRunning="
				+ mysqlRunning + ", mysqlSock=" + mysqlSock + ", hostDataTimestamp=" + hostDataTimestamp
				+ ", reportHostName=" + reportHostName + ", mysqlVersion=" + mysqlVersion + ", innodbVersion="
				+ innodbVersion + ", readOnly=" + readOnly + ", masterActiveCount=" + masterActiveCount
				+ ", slaveCount=" + slaveCount + ", slaveHostName=" + slaveHostName + ", masterHostName="
				+ masterHostName + ", mysqlDataTimestamp=" + mysqlDataTimestamp + "]";
	}


	
	
	
/*
	public ConsoleDTO(String tableName, String clusterName, int mysqlNodeCount) {
		super();
		this.tableName = tableName;
		this.clusterName = clusterName;
		this.mysqlNodeCount = mysqlNodeCount;
	}

	

	public ConsoleDTO(String tableName, String tableSchema, String clusterName, int mysqlNodeCount) {
		super();
		this.tableName = tableName;
		this.tableSchema = tableSchema;
		this.clusterName = clusterName;
		this.mysqlNodeCount = mysqlNodeCount;
	}

	*/
	


	
	

}
