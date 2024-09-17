package br.com.m4rc310.persona.api.services.weather;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.DtoDailyWeather;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.dto.weather.WearcherAlert;
import br.com.m4rc310.persona.api.services.MService;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.dto.MWeatherCurrentWeather;
import br.com.m4rc310.weather.dto.MWeatherDaily;
import br.com.m4rc310.weather.dto.MWeatherCurrent.Rain;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class WeatherNowService extends MService {
	
	@GraphQLQuery(name = AMOUNT$temperature, description = DESC$amount_temperature)
	public BigDecimal getTemperature(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getTemperature();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = AMOUNT$feels_like, description = DESC$amount_feels_like)
	public BigDecimal getFeelsLike(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getFeelsLike();
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

	@MDate(unixFormat = true, value = DATE_ISO_MASK)
	@GraphQLQuery(name = DATE$hour_sunset, description = DESC$date_hour_sunset)
	public Long getDateSunset(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateSunSet();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = DATE_ISO_MASK)
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

	@GraphQLQuery(name = NAME$state, description = DESC$name_state)
	public String getStateName(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getGeolocation().getRegion();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = DATE_ISO_MASK)
	@GraphQLQuery(name = DATE$weatcher, description = DESC$date_weatcher)
	public Long getDateWeatcher(@GraphQLContext DtoWeatcherData data) {
		try {
			return data.getWeather().getCurrent().getDateWeather();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = LIST$alerts, description = DESC$list_alerts)
	public List<WearcherAlert> listAlert(@GraphQLContext DtoWeatcherData data) {
		return data.getAlerts();
	}

	
	
	
	@GraphQLQuery(name=PERCENT$pop_daily, description=DESC$percent_pop_daily)
	public BigDecimal dailyPop (@GraphQLContext DtoWeatcherData data) {
		try {
			MWeather weather = data.getWeather();
			MWeatherDaily daily = weather.getDaily().get(0);
			return daily.getPop().multiply(BigDecimal.valueOf(100));	
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	@GraphQLQuery(name=AMOUNT$rain_daily, description=DESC$amount_rain_daily)
	public BigDecimal dailyRain (@GraphQLContext DtoWeatcherData data) {
		try {
			MWeather weather = data.getWeather();
			MWeatherDaily daily = weather.getDaily().get(0);
			return daily.getRain();		
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}
	
	@GraphQLQuery(name = LIST$weather_daily, description = DESC$list_weather_daily)
	public List<DtoDailyWeather> getListDaily(@GraphQLContext DtoWeatcherData data){
		List<DtoDailyWeather> list = new ArrayList<>();
		try {
			data.getWeather().getDaily().forEach(daily -> {
				DtoDailyWeather dailyWeather = new DtoDailyWeather();
				dailyWeather.setDaily(daily);
				list.add(dailyWeather);
			});
		} catch (Exception e) {
		}
		return list;
	}
}
