package com.bae.raziel.admin;


public class MySQLHostDto {
	
	
	private String hostName;
	
	private String clusterName;

	private int hostType;

	
	
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

	public int getHostType() {
		return hostType;
	}

	public void setHostType(int hostType) {
		this.hostType = hostType;
	}

	@Override
	public String toString() {
		return "MySQLHostNode [hostName=" + hostName + ", clusterName=" + clusterName + ", hostType=" + hostType + "]";
	}
	
	
	
	
	
	
	
}
