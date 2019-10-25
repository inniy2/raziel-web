package com.bae.raziel.command;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@IdClass(AlterRegisterId.class)
@Table(name = "alter_register")
public class AlterRegisterDTO {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private long id;
	
	
	@Id
	@Column(name = "table_name", nullable = false)
	private String tableName;
	
	@Id
	@Column(name = "cluster_name", nullable = false)
	private String clusterName;
	
	@Id
	@Column(name = "table_schema", nullable = false)
	private String tableSchema;
	
	
	@Column(name = "checkReplicaList", nullable = false)
	private ArrayList<String> checkReplicaList;
	
	@Column(name = "alter_statement", nullable = false)
	private ArrayList<String> alterStatement;
	
	@Column(name = "register_email", nullable = false)
	private String registerEmail;
	
	@Column(name = "commance_timestamp", nullable = false)
	private Timestamp commanceTimestamp;
	
	@Column(name = "progress_status", nullable = false)
	private int progressStatus;
	
	@Column(name = "execution_time", nullable = false)
	private long executionTime;
	
	@CreationTimestamp
	@Column(name = "create_timestamp", nullable = false)
	private LocalDateTime createTimestamp;
	 
	@UpdateTimestamp
	@Column(name = "update_timestamp", nullable = true)
	private LocalDateTime updateTimestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
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

	public String getRegisterEmail() {
		return registerEmail;
	}

	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}

	public Timestamp getCommanceTimestamp() {
		return commanceTimestamp;
	}

	public void setCommanceTimestamp(Timestamp commanceTimestamp) {
		this.commanceTimestamp = commanceTimestamp;
	}

	public int getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(int progressStatus) {
		this.progressStatus = progressStatus;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public LocalDateTime getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(LocalDateTime createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public LocalDateTime getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(LocalDateTime updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Override
	public String toString() {
		return "AlterRegisterDTO [id=" + id + ", tableName=" + tableName + ", clusterName=" + clusterName
				+ ", tableSchema=" + tableSchema + ", checkReplicaList=" + checkReplicaList + ", alterStatement="
				+ alterStatement + ", registerEmail=" + registerEmail + ", commanceTimestamp=" + commanceTimestamp
				+ ", progressStatus=" + progressStatus + ", executionTime=" + executionTime + ", createTimestamp="
				+ createTimestamp + ", updateTimestamp=" + updateTimestamp + "]";
	}

    
	
	
	

}
