package com.naorsantos.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("deprecation")
public class EmailDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message= "Preenchimento Obrigatório")
	@Email(message = "Email Invalido")
	private String email;
	
	public EmailDTO() {
		
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
