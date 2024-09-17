package br.com.m4rc310.persona.api.services.weather;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.DtoDailyWeather;
import br.com.m4rc310.persona.api.services.MService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class WeatherDailyService extends MService {
	
	@MDate(unixFormat = true, value = DATE_ISO_MASK)
	@GraphQLQuery(name=DATE$daily_weather, description=DESC$date_daily_weather)
	public Long dateWeather (@GraphQLContext DtoDailyWeather daily) {
		try {
			log.info("call date daily weather.");
			return daily.getDaily().getDate();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLQuery(name=PERCENT$pop_daily, description=DESC$percent_pop_daily)
	public BigDecimal dailyPop (@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getPop().multiply(BigDecimal.valueOf(100));	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	@GraphQLQuery(name=AMOUNT$rain_daily, description=DESC$amount_rain_daily)
	public BigDecimal dailyRain (@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getRain();	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	
	@GraphQLQuery(name=AMOUNT$temp_daily, description=DESC$amount_temp_daily)
	public BigDecimal dailyTemp (@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getTemp().getDay();	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@GraphQLQuery(name=AMOUNT$temp_min_daily, description=DESC$amount_temp_min_daily)
	public BigDecimal dailyTempMin (@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getTemp().getMin();	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	
	@GraphQLQuery(name=AMOUNT$temp_max_daily, description=DESC$amount_temp_max_daily)
	public BigDecimal dailyTempMax (@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getTemp().getMax();	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	
	@GraphQLQuery(name=INFO$daily_summary, description=DESC$info_daily_summary)
	public String dailySummary(@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getSummary();	
		} catch (Exception e) {
			return "";
		}
	}

	@GraphQLQuery(name = CODE$icon, description = DESC$code_icon)
	public String dailyCodeIcon(@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getWeather().get(0).getIcon();	
		} catch (Exception e) {
			return "";
		}
	}
	
	@GraphQLQuery(name = INFO$description, description = DESC$info_description)
	public String dailyDescription(@GraphQLContext DtoDailyWeather daily) {
		try {
			return daily.getDaily().getWeather().get(0).getDescription();	
		} catch (Exception e) {
			return "";
		}
	}
	
	
}
