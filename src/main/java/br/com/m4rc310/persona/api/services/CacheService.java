package br.com.m4rc310.persona.api.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;

@Service
@EnableCaching
public class CacheService extends MService {
	
	private static final String CACHE_KEY_IP = "cache_key_ip";
	private static final String CACHE_CLIENT_INFO = "cache_client_info";
	
	
	@Cacheable(CACHE_KEY_IP)
	public DtoGeolocation getGeolocationFromIp(String ip) throws Exception {
		return super.getGeolocationFromIp(ip);
	}
	
	
	@Cacheable(CACHE_CLIENT_INFO)
	public DtoGeolocation getGeolocationClient() throws Exception {
		String ip = flux.getIPClient();
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "143.0.116.110";
		}
		return getGeolocationFromIp(ip);
	}
	
	
	
}
