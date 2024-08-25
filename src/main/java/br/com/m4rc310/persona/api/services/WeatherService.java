package br.com.m4rc310.persona.api.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.dto.MWeatherCurrent.Rain;
import br.com.m4rc310.weather.dto.MWeatherCurrentWeather;
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
	
	private final List<BigDecimal> GEO = Arrays.asList(BigDecimal.valueOf(-24.046), BigDecimal.valueOf(-52.3838));

	@Autowired
	private MWeatherService weatherService;

	// @Cacheable(CACHE_WEATCHER_KEY)
	// @GraphQLQuery(name = QUERY$weather, description = DESC$query_weather)
	public DtoWeatcherData getWeather() throws Exception {
		log.info("from api");
		MWeather data = weatherService.getMWeather(GEO.get(0), GEO.get(1));
		
		
		return DtoWeatcherData.from(data);
	}

	@Cacheable(CACHE_WEATCHER_KEY)
	@GraphQLQuery(name = QUERY$weather, description = DESC$query_weather)
	public DtoWeatcherData getWeatherFrom() throws Exception {
		log.info("from api");
		MWeather data = weatherService.getMWeather(GEO.get(0), GEO.get(1));
		return DtoWeatcherData.from(data);
	}

	@Scheduled(cron = "0 */15 * * * *")
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

	@GraphQLQuery(name = AMOUNT$rain, description = DESC$amount_rain)
	public BigDecimal getAmountRain(@GraphQLContext DtoWeatcherData data) {
		try {
			Rain rain = data.getWeather().getCurrent().getRain();
			return rain.getPrecipitation();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	
	@GraphQLQuery(name=INFO$weather, description=DESC$info_weather)
	public String getWeacherDetails(@GraphQLContext DtoWeatcherData data) {
		String detail = "";
		try {
			MWeatherCurrentWeather currentWeather = data.getWeather().getCurrent().getWeather().get(0);
			detail = currentWeather.getDescription();
		} catch (Exception e) {
		}
		return detail;
	}
	
	@GraphQLQuery(name=CODE$icon, description=DESC$code_icon)
	public String getWeacherIconRef(@GraphQLContext DtoWeatcherData data) {
		String detail = "";
		try {
			MWeatherCurrentWeather currentWeather = data.getWeather().getCurrent().getWeather().get(0);
			detail = currentWeather.getIcon();
		} catch (Exception e) {
		}
		return detail;
	}
	
	

}