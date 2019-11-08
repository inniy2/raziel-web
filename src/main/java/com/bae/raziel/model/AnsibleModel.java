package com.bae.raziel.model;

public class AnsibleModel {

	private String clusterName;
	private String ghostHostName;
	private int port;
	private String tableName;
	
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getGhostHostName() {
		return ghostHostName;
	}
	public void setGhostHostName(String ghostHostName) {
		this.ghostHostName = ghostHostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Override
	public String toString() {
		return "AnsibleModel [clusterName=" + clusterName + ", ghostHostName=" + ghostHostName + ", port=" + port
				+ ", tableName=" + tableName + "]";
	}
	
	
	
	
	
}
