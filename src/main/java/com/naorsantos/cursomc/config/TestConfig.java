package com.naorsantos.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.naorsantos.cursomc.services.EmailService;
import com.naorsantos.cursomc.services.MockEmailService;
import com.naorsantos.cursomc.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public boolean instatianteDatabase() {
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	
}
