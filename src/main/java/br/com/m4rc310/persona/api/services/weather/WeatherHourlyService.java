package br.com.m4rc310.persona.api.services.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.weather.DtoHourlyWeather;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.services.MService;
import br.com.m4rc310.weather.dto.MWeatherHourly;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class WeatherHourlyService extends MService {
	
	@GraphQLQuery(name=LIST$hourly_weather, description=DESC$list_hourly_weather)
	public List<DtoHourlyWeather> getListHourly(@GraphQLContext DtoWeatcherData data){
		List<DtoHourlyWeather> list = new ArrayList<>();
		try {
			data.getWeather().getHourly().forEach(hourly -> {
				DtoHourlyWeather dtoHourly = new DtoHourlyWeather();
				dtoHourly.setHourly(hourly);
				list.add(dtoHourly);
			});
		} catch (Exception e) {
		}
		return list;
	};
	
	@MDate(unixFormat = true)
	@GraphQLQuery(name = DATE$weatcher)
	public Long dateHourly (@GraphQLContext DtoHourlyWeather hourly) {
		try {
			MWeatherHourly apiHourly = hourly.getHourly();
			return apiHourly.getDate();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=AMOUNT$rain_in_hour, description=DESC$amount_rain_in_hour)
	public BigDecimal getRain (@GraphQLContext DtoHourlyWeather hourly) {
		try {
			MWeatherHourly apiHourly = hourly.getHourly();
			return apiHourly.getRain().getAmount();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@GraphQLQuery(name=PERCENT$pop_hour, description=DESC$percent_pop_hour)
	public BigDecimal getPop (@GraphQLContext DtoHourlyWeather hourly) {
		try {
			MWeatherHourly apiHourly = hourly.getHourly();
			return apiHourly.getPop().multiply(BigDecimal.valueOf(100));
		} catch (Exception e) {
			return null;
		}
	}
	
}
