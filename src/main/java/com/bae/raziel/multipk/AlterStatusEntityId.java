package com.bae.raziel.multipk;

import java.io.Serializable;

public class AlterStatusEntityId  implements Serializable {

	private String tableName;
	private String clusterName;
	private String tableSchema;
	
	public AlterStatusEntityId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AlterStatusEntityId(String tableName, String clusterName, String tableSchema) {
		super();
		this.tableName = tableName;
		this.clusterName = clusterName;
		this.tableSchema = tableSchema;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clusterName == null) ? 0 : clusterName.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((tableSchema == null) ? 0 : tableSchema.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlterStatusEntityId other = (AlterStatusEntityId) obj;
		if (clusterName == null) {
			if (other.clusterName != null)
				return false;
		} else if (!clusterName.equals(other.clusterName))
			return false;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		if (tableSchema == null) {
			if (other.tableSchema != null)
				return false;
		} else if (!tableSchema.equals(other.tableSchema))
			return false;
		return true;
	}
	
	
}
