package br.com.m4rc310.persona.api.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.m4rc310.persona.api.dto.weather.DtoGeolocation;
import br.com.m4rc310.persona.api.dto.weather.DtoGeolocationResponse;

@Service
@EnableCaching
public class CacheService {
	
	private static final String CACHE_KEY_IP = "cache_key_ip";
	
	@Autowired
	private ObjectMapper mapper;
	
	@Cacheable(CACHE_KEY_IP)
	public DtoGeolocation getGeolocationFromIp(String ip) {
		String suri = String.format("http://ip-api.com/json/%s", ip);
		try {
			URL uri = new URI(suri).toURL();
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			connection.setRequestProperty("accept", "application/json");

			InputStream responseStream = connection.getInputStream();
			
			String json = new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);
			DtoGeolocationResponse resp = mapper.readValue(json, DtoGeolocationResponse.class);
			
			if (resp.getStatus().equals("fail")) {
				throw new UnsupportedOperationException();
			}
			
			return DtoGeolocation.from(resp.getLatitude(), resp.getLongitude());
		} catch (Exception e) {
			return DtoGeolocation.from(BigDecimal.valueOf(-24.046), BigDecimal.valueOf(-52.3838));
		}
	}
}
