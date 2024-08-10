package br.com.m4rc310.persona.api;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.m4rc310.persona.api")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:/security.properties")
public class App {
	
	
	@Autowired
	@SuppressWarnings("unused")
	private PasswordEncoder pe;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
	}
}
