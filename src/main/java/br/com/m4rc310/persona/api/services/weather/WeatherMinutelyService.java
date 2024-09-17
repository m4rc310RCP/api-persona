package br.com.m4rc310.persona.api.services.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.weather.DtoMinutelyWeather;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.services.MService;
import br.com.m4rc310.weather.dto.MWeatherMinutely;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class WeatherMinutelyService extends MService {
	
	@GraphQLQuery(name=LIST$weather_minutely, description=DESC$list_weather_minutely)
	public List<DtoMinutelyWeather> getListDaily(@GraphQLContext DtoWeatcherData data){
		List<DtoMinutelyWeather> list = new ArrayList<>();
		try {
			data.getWeather().getMinutely().forEach(minute -> {
				DtoMinutelyWeather minutely  = new DtoMinutelyWeather();
				minutely.setMinutely(minute);
				list.add(minutely);
			});
		} catch (Exception e) {
		}
		return list;
	}
	
	@MDate(unixFormat = true)
	@GraphQLQuery(name=DATE$weather_minutely, description=DESC$date_weather_minutely)
	public Long dateMinutely(@GraphQLContext DtoMinutelyWeather minute) {
		try {
			MWeatherMinutely minutely = minute.getMinutely();
			return minutely.getDate();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=AMOUNT$rain_minutely, description=DESC$amount_rain_minutely)
	public BigDecimal amountPrecipidation(@GraphQLContext DtoMinutelyWeather minute) {
		try {
			MWeatherMinutely minutely = minute.getMinutely();
			return minutely.getPrecipitation();
		} catch (Exception e) {
			return null;
		}
	}
	
}
