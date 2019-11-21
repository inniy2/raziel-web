package com.bae.raziel.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "alter_history")
public class AlterHistEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "cluster_name", nullable = false)
	private String clusterName;
	
	@Column(name = "table_schema", nullable = false)
	private String tableSchema;
	
	@Column(name = "table_name", nullable = false)
	private String tableName;
	
	@Column(name = "ghost_host_name", nullable = false)
	private String ghostHostName;
	/*
	@Column(name = "check_replica_list", nullable = false)
	private ArrayList<String> checkReplicaList;
	
	@Column(name = "alter_statement", nullable = false)
	private ArrayList<String> alterStatement;
	*/	
	@Column(name = "register_email", nullable = false)
	private String registerEmail;
	
	@Column(name = "register_timestamp", nullable = true)
	private Timestamp registerTimestamp;
	
	@Column(name = "create_timestamp", nullable = true)
	private Timestamp createTimestamp;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getGhostHostName() {
		return ghostHostName;
	}

	public void setGhostHostName(String ghostHostName) {
		this.ghostHostName = ghostHostName;
	}
/*
	public ArrayList<String> getCheckReplicaList() {
		return checkReplicaList;
	}

	public void setCheckReplicaList(ArrayList<String> checkReplicaList) {
		this.checkReplicaList = checkReplicaList;
	}

	public ArrayList<String> getAlterStatement() {
		return alterStatement;
	}

	public void setAlterStatement(ArrayList<String> alterStatement) {
		this.alterStatement = alterStatement;
	}
*/
	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public Timestamp getRegisterTimestamp() {
		return registerTimestamp;
	}

	public void setRegisterTimestamp(Timestamp registerTimestamp) {
		this.registerTimestamp = registerTimestamp;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	
	
/*
	@Override
	public String toString() {
		return "AlterHistEntity [id=" + id + ", clusterName=" + clusterName + ", tableSchema=" + tableSchema
				+ ", tableName=" + tableName + ", ghostHostName=" + ghostHostName + ", checkReplicaList="
				+ checkReplicaList + ", alterStatement=" + alterStatement + ", registerEmail=" + registerEmail
				+ ", registerTimestamp=" + registerTimestamp + ", createTimestamp=" + createTimestamp + "]";
	}
*/
	
	
}
