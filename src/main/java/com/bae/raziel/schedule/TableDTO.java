package com.bae.raziel.schedule;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TableId.class)
@Table(name = "table_node")
public class TableDTO {

	@Id
	private long id;

	@Column(name = "host_name", nullable = true)
	private String hostName;
	
	@Column(name = "cluster_name", nullable = true)
	private String clusterName;
	
	@Id
	@Column(name = "table_schema", nullable = true)
	private String tableSchema;
	
	@Id
	@Column(name = "table_name", nullable = true)
	private String tableName;
	
	@Column(name = "data_length", nullable = true)
	private long dataLength;
	
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

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getDataLength() {
		return dataLength;
	}

	public void setDataLength(long dataLength) {
		this.dataLength = dataLength;
	}

	public Timestamp getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(Timestamp dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}

	@Override
	public String toString() {
		return "TableDTO [id=" + id + ", hostName=" + hostName + ", clusterName=" + clusterName + ", tableSchema="
				+ tableSchema + ", tableName=" + tableName + ", dataLength=" + dataLength + ", dataTimestamp="
				+ dataTimestamp + "]";
	}


	
	

}
