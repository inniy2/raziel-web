package com.bae.raziel.ghost;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GhostDto {

	private long orderId;
	
	private String clusterName;
	private String tableSchema;
	private String tableName;
	private String ghostHostName;
	
	private ArrayList<String> checkReplicaList;
	private ArrayList<String> alterStatement;
	
	private String dataDir;
	private long availableSpace;
	private float diskUsages;
	
	private String registerEmail;
	
	private List<String> outputStrList;
	
	private Timestamp createTimestamp;
	private Timestamp updateTimestamp;
	private int progressStatus;
	
	
	/*
	 * GHC
	 */
	private long id;
	private Timestamp lastUpdate;
	private String hint;
	private String value;
	
	
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
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
	public String getDataDir() {
		return dataDir;
	}
	public void setDataDir(String dataDir) {
		this.dataDir = dataDir;
	}
	public long getAvailableSpace() {
		return availableSpace;
	}
	public void setAvailableSpace(long availableSpace) {
		this.availableSpace = availableSpace;
	}
	public float getDiskUsages() {
		return diskUsages;
	}
	public void setDiskUsages(float diskUsages) {
		this.diskUsages = diskUsages;
	}
	public String getRegisterEmail() {
		return registerEmail;
	}
	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	public List<String> getOutputStrList() {
		return outputStrList;
	}
	public void setOutputStrList(List<String> outputStrList) {
		this.outputStrList = outputStrList;
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
	public int getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(int progressStatus) {
		this.progressStatus = progressStatus;
	}
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return "GhostDto [orderId=" + orderId + ", clusterName=" + clusterName + ", tableSchema=" + tableSchema
				+ ", tableName=" + tableName + ", ghostHostName=" + ghostHostName + ", checkReplicaList="
				+ checkReplicaList + ", alterStatement=" + alterStatement + ", dataDir=" + dataDir + ", availableSpace="
				+ availableSpace + ", diskUsages=" + diskUsages + ", registerEmail=" + registerEmail
				+ ", outputStrList=" + outputStrList + ", createTimestamp=" + createTimestamp + ", updateTimestamp="
				+ updateTimestamp + ", progressStatus=" + progressStatus + ", id=" + id + ", lastUpdate=" + lastUpdate
				+ ", hint=" + hint + ", value=" + value + "]";
	}
	
	
	
	
}
