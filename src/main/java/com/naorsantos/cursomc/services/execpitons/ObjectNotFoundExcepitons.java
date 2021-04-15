package com.naorsantos.cursomc.services.execpitons;

public class ObjectNotFoundExcepitons extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcepitons(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundExcepitons(String msg, Throwable cause) {
		super(msg);
	}
}
