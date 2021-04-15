package com.naorsantos.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.naorsantos.cursomc.services.execpitons.ObjectNotFoundExcepitons;

@ControllerAdvice
public class ResourceExcepitonHandler {
	
	public ResponseEntity<StandarError> objecNotFound(ObjectNotFoundExcepitons e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

}
