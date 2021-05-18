package com.naorsantos.cursomc.services.execpitons;

public class DataIntegratedExcepitons extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegratedExcepitons(String msg) {
		super(msg);
	}
	
	public DataIntegratedExcepitons(String msg, Throwable cause) {
		super(msg);
	}
}
