package com.bae.raziel.ghost;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;



@Entity
@IdClass(GhostEntityId.class)
@Table(name = "alter_history")
public class GhostEntity {

	@Id
	@Column(name = "cluster_name", nullable = false)
	private String clusterName;
	
	@Id
	@Column(name = "table_schema", nullable = false)
	private String tableSchema;
	
	@Id
	@Column(name = "table_name", nullable = false)
	private String tableName;
	
	
	
	@Column(name = "ghost_host_name", nullable = false)
	private String ghostHostName;
	
	@Column(name = "check_replica_list", nullable = false)
	private ArrayList<String> checkReplicaList;
	
	@Column(name = "alter_statement", nullable = false)
	private ArrayList<String> alterStatement;
	
	@Column(name = "progress_status", nullable = false)
	private int progressStatus;
	
	@Column(name = "register_email", nullable = false)
	private String registerEmail;
	
	@Column(name = "create_timestamp", nullable = true)
	private Timestamp createTimestamp;
	
	@Column(name = "update_timestamp", nullable = true)
	private Timestamp updateTimestamp;

	

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

	public int getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(int progressStatus) {
		this.progressStatus = progressStatus;
	}

	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Override
	public String toString() {
		return "GhostEntity [clusterName=" + clusterName + ", tableSchema=" + tableSchema + ", tableName=" + tableName
				+ ", ghostHostName=" + ghostHostName + ", checkReplicaList=" + checkReplicaList + ", alterStatement="
				+ alterStatement + ", progressStatus=" + progressStatus + ", registerEmail=" + registerEmail
				+ ", createTimestamp=" + createTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
	}

	
	
}
