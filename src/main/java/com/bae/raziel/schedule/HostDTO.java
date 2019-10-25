package com.bae.raziel.schedule;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "host_node")
public class HostDTO {

	

	@Id
	private long id;

	@Column(name = "host_name", nullable = true)
	private String hostName;
	
	@Column(name = "cluster_name", nullable = true)
	private String clusterName;
	
	@Column(name = "cpu_percentage", nullable = true)
	private float cpuPercentage;
	
	@Column(name = "free_disk_percentage", nullable = true)
	private float freeDiskPercentage;
	
	@Column(name = "total_disk_size", nullable = true)
	private long totalDiskSize;
	
	@Column(name = "free_disk_size", nullable = true)
	private long freeDiskSize;
	
	@Column(name = "mysql_data_size", nullable = true)
	private long mysqlDataSize;
	
	@Column(name = "ghost_version", nullable = true)
	private String ghostVersion;
	
	@Column(name = "ghost_sock_count", nullable = true)
	private int ghostSockCount;
	
	@Column(name = "ghost_postpone_file", nullable = true)
	private boolean ghostPostponeFile;
	
	@Column(name = "ghost_running", nullable = true)
	private boolean ghost_running;
	
	@Column(name = "mysql_pid", nullable = true)
	private boolean mysqlPid;
	
	@Column(name = "mysql_running", nullable = true)
	private boolean mysqlRunning;
	
	@Column(name = "mysql_sock", nullable = true)
	private boolean mysqlSock;

	@Column(name ="data_timestamp", nullable = true)
	private Timestamp dataTimestamp;

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

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
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

	public boolean isGhost_running() {
		return ghost_running;
	}

	public void setGhost_running(boolean ghost_running) {
		this.ghost_running = ghost_running;
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

	public Timestamp getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(Timestamp dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}

	@Override
	public String toString() {
		return "HostDTO [id=" + id + ", hostName=" + hostName + ", clusterName=" + clusterName + ", cpuPercentage="
				+ cpuPercentage + ", freeDiskPercentage=" + freeDiskPercentage + ", totalDiskSize=" + totalDiskSize
				+ ", freeDiskSize=" + freeDiskSize + ", mysqlDataSize=" + mysqlDataSize + ", ghostVersion="
				+ ghostVersion + ", ghostSockCount=" + ghostSockCount + ", ghostPostponeFile=" + ghostPostponeFile
				+ ", ghost_running=" + ghost_running + ", mysqlPid=" + mysqlPid + ", mysqlRunning=" + mysqlRunning
				+ ", mysqlSock=" + mysqlSock + ", dataTimestamp=" + dataTimestamp + "]";
	}

}
