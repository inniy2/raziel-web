package com.bae.raziel.model;

import java.sql.Timestamp;

public class GhcModel {


	private long id;
	private Timestamp lastUpdate;
	private String hint;
	private String value;
	
	private String serviceMessage;
	private String repoMessage;
	
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
	public String getServiceMessage() {
		return serviceMessage;
	}
	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}
	public String getRepoMessage() {
		return repoMessage;
	}
	public void setRepoMessage(String repoMessage) {
		this.repoMessage = repoMessage;
	}
	
	
	
	
}
