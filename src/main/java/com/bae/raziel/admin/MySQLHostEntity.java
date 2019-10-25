package com.bae.raziel.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
@Table(name = "mysql_host")
public class MySQLHostEntity {
	
	
	@Id
	@Column(name = "mysql_host_name", nullable = false)
	private String mysqlHostName;
	
	@Column(name = "cluster_name", nullable = false)
	private String clusterName;

	@Column(name = "host_type", nullable = false)
	private int hostType;

	
	

	
	public String getMysqlHostName() {
		return mysqlHostName;
	}

	public void setMysqlHostName(String mysqlHostName) {
		this.mysqlHostName = mysqlHostName;
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
		return "MySQLHostEntity [mysqlHostName=" + mysqlHostName + ", clusterName=" + clusterName + ", hostType="
				+ hostType + "]";
	}


	
	
}
