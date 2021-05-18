package com.naorsantos.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.naorsantos.cursomc.services.execpitons.AuthorizationExcepiton;
import com.naorsantos.cursomc.services.execpitons.DataIntegratedExcepitons;
import com.naorsantos.cursomc.services.execpitons.ObjectNotFoundExcepitons;

@ControllerAdvice
public class ResourceExcepitonHandler {
	
	@ExceptionHandler(ObjectNotFoundExcepitons.class)
	public ResponseEntity<StandarError> objecNotFound(ObjectNotFoundExcepitons e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegratedExcepitons.class)
	public ResponseEntity<StandarError> dataIntegrity(DataIntegratedExcepitons e, HttpServletRequest request){
			
			StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
			
			ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Errors",System.currentTimeMillis());
			
			for(FieldError x : e.getBindingResult().getFieldErrors()) {
				err.addError(x.getField(), x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
	
	@ExceptionHandler(AuthorizationExcepiton.class)
	public ResponseEntity<StandarError> authorization(AuthorizationExcepiton e, HttpServletRequest request){
		
		StandarError err = new StandarError(HttpStatus.FORBIDDEN.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
}
