package br.com.m4rc310.persona.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.services.MWeatherService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class WeatherService extends MService {
	
	private static final String CACHE_WEATCHER_KEY = "cache_weatcher_key";

	@Autowired
	private MWeatherService weatherService;

	@GraphQLQuery(name = QUERY$weather, description = DESC$query_weather)
	@Cacheable(CACHE_WEATCHER_KEY)
	public DtoWeatcherData getWeather() throws Exception {
		log.info("from api");
		BigDecimal lat = BigDecimal.valueOf(-24.046);
		BigDecimal lon = BigDecimal.valueOf(-52.3838);
		MWeather data = weatherService.getMWeather(lat, lon);
		return DtoWeatcherData.from(data);
	}
	
	@Scheduled(cron = "0 */5 * * * *")
	@CacheEvict(CACHE_WEATCHER_KEY)
	public void autoResetCache() {
		log.info("reset cache");
	}

	@GraphQLQuery(name = AMOUNT$temperature, description = DESC$amount_temperature)
	public BigDecimal getTemperature(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getTemperature();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = PERCENT$clouds, description = DESC$percent_clouds)
	public BigDecimal getCloudPersent(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getClouds();
		} catch (Exception e) {
			return null;
		}
	}

}