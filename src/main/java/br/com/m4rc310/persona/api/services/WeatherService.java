package br.com.m4rc310.persona.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;
import br.com.m4rc310.gql.mappers.annotations.MDate;
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
	

	@Autowired
	private MWeatherService weatherService;
	
	@Autowired
	private CacheService cacheService;

	@Cacheable(CACHE_WEATCHER_KEY)
	@GraphQLQuery(name = QUERY$weather, description = DESC$query_weather)
	public DtoWeatcherData getWeatherFrom() throws Exception {
		String ip = flux.getIPClient();
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "143.0.116.110";
		}
		DtoGeolocation geo = cacheService.getGeolocationFromIp(ip);
		MWeather weather = weatherService.getMWeather(geo.getLatitude(), geo.getLongitude());
		DtoWeatcherData data = new DtoWeatcherData();
		data.setGeolocation(geo);
		data.setWeather(weather);;
		return data;
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
	
	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name=DATE$hour_sunset, description=DESC$date_hour_sunset)
	public Long getDateSunset(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateSunSet();
		} catch (Exception e) {
			return null;
		}
	}
	
	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name=DATE$hour_sunrise, description=DESC$date_hour_sunrise)
	public Long getDateSunrise(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateSunRise();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=AMONT$speed_wind, description=DESC$amont_speed_wind)
	public BigDecimal getSpeedWind(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getSpeedWind();
		} catch (Exception e) {
			return null;
		}
	}
	
	 @GraphQLQuery(name=NAME$city, description=DESC$name_city)
	public String getCityNameWeatcher(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getGeolocation().getCity();
		} catch (Exception e) {
			return null;
		}
	}
	
}