package com.bae.raziel.schedule;

import java.io.Serializable;


public class TableId implements Serializable {
	
	private long id;
	private String tableSchema;
	private String tableName;
	
	
	
	public TableId() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TableId(long id, String tableSchema, String tableName) {
		super();
		this.id = id;
		this.tableSchema = tableSchema;
		this.tableName = tableName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		TableId other = (TableId) obj;
		if (id != other.id)
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
