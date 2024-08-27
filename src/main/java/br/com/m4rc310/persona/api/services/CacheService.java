package br.com.m4rc310.persona.api.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;

@Service
@EnableCaching
public class CacheService extends MService {
	
	private static final String CACHE_KEY_IP = "cache_key_ip";
	
	
	@Cacheable(CACHE_KEY_IP)
	public DtoGeolocation getGeolocationFromIp(String ip) throws Exception {
		return super.getGeolocationFromIp(ip);
	}
}
