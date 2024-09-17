package br.com.m4rc310.persona.api.services.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;
import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.DtoDailyWeather;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.dto.weather.WearcherAlert;
import br.com.m4rc310.persona.api.services.CacheService;
import br.com.m4rc310.persona.api.services.MService;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.dto.MWeatherAlert;
import br.com.m4rc310.weather.dto.MWeatherCurrent.Rain;
import br.com.m4rc310.weather.dto.MWeatherCurrentWeather;
import br.com.m4rc310.weather.dto.MWeatherDaily;
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

	
	

}