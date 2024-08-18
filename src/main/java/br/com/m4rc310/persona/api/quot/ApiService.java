package br.com.m4rc310.persona.api.quot;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiService {
	
	@PostConstruct
	void init() {
		log.info("Init");
	}
}
