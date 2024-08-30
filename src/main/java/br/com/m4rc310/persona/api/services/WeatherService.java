package br.com.m4rc310.persona.api.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;
import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.dto.weather.WearcherAlert;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.dto.MWeatherAlert;
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
		data.setWeather(weather);
		data.setAlerts(new ArrayList<>());
		
		List<MWeatherAlert> alerts = weather.getAlerts();
				
		if (alerts != null) {
			alerts.forEach(alert -> {
				WearcherAlert wa = new WearcherAlert();
				wa.setWeatherAlert(alert);
				data.getAlerts().add(wa);
			});
		}
		
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

	@GraphQLQuery(name = INFO$weather, description = DESC$info_weather)
	public String getWeacherDetails(@GraphQLContext DtoWeatcherData data) {
		String detail = "";
		try {
			MWeatherCurrentWeather currentWeather = data.getWeather().getCurrent().getWeather().get(0);
			detail = currentWeather.getDescription();
		} catch (Exception e) {
		}
		return detail;
	}

	@GraphQLQuery(name = CODE$icon, description = DESC$code_icon)
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
	@GraphQLQuery(name = DATE$hour_sunset, description = DESC$date_hour_sunset)
	public Long getDateSunset(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateSunSet();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name = DATE$hour_sunrise, description = DESC$date_hour_sunrise)
	public Long getDateSunrise(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateSunRise();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = AMONT$speed_wind, description = DESC$amont_speed_wind)
	public BigDecimal getSpeedWind(@GraphQLContext DtoWeatcherData data) {
		try {
			BigDecimal multi = BigDecimal.valueOf(3.6);
			return data.getWeather().getCurrent().getSpeedWind().multiply(multi);
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = AMOUNT$humidit, description = DESC$amount_humidit)
	public BigDecimal getHumidity(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getHumidity();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = INFO$main, description = DESC$info_main)
	public String getMain(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getWeather().get(0).getMain();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = NAME$city, description = DESC$name_city)
	public String getCityNameWeatcher(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getGeolocation().getCity();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name=DATE$weatcher, description=DESC$date_weatcher)
	public Long getDateWeatcher(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateWeather();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=LIST$alerts, description=DESC$list_alerts)
	public List<WearcherAlert> listAlert(@GraphQLContext DtoWeatcherData data){
		return data.getAlerts();
	}
	
	@GraphQLQuery(name=NAME$sender, description=DESC$name_sender)
	public String getAlertSender(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getSender();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=INFO$event, description=DESC$info_event)
	public String getAlertEvent(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getEvent();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=INFO$description, description=DESC$info_description)
	public String getAlertDescription(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDescription();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name=DATE$init, description=DESC$date_init)
	public Long getAlertDateStart(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDateStart();
		} catch (Exception e) {
			return null;
		}
	}
	
	@MDate(unixFormat = true, value = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
	@GraphQLQuery(name=DATE$end, description=DESC$date_end)
	public Long getAlertDateEnd(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDateEnd();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	

}