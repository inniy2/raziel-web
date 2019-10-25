package com.bae.raziel.mysql;

import java.sql.Timestamp;

public class MySQLDao {
	
	private String hostName;
	private String user;
	private String password;
	private int port;
	
	private String tableSchema;
	private String tableName;
	private long tableLength;
	
	private String readOnly;
	
	/*
	 * GHC
	 */
	private long id;
	private Timestamp lastUpdate;
	private String hint;
	private String value;
	
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
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
	public long getTableLength() {
		return tableLength;
	}
	public void setTableLength(long tableLength) {
		this.tableLength = tableLength;
	}
	
	
	
	public String getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
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
	/*
	 * Use it in AnsibleService
	 * 
	 */
	@Override
	public String toString() {
		return "MySQLDao [hostName=" + hostName + ", tableSchema=" + tableSchema + ", tableName=" + tableName
				+ ", tableLength=" + tableLength + "]";
	}
	
	
	

}
