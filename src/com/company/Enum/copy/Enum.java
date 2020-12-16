package com.company.Enum.copy;

public enum Enum {
	WARN("the id is too short");
	
	public final String error;
	
	
	Enum(String error) {
		this.error = error;

	}
	public String getError() {
		return error;
	}
	

	
	
	@Override
	public String toString() {
		return error;
	}
	
	
	
}
