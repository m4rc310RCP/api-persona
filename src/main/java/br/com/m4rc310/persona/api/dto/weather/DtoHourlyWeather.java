package br.com.m4rc310.persona.api.dto.weather;

import java.io.Serializable;

import br.com.m4rc310.persona.api.i18n.IConsts;
import br.com.m4rc310.weather.dto.MWeatherHourly;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = IConsts.TYPE$hourly_weather, description = IConsts.DESC$type_hourly_weather)
public class DtoHourlyWeather implements Serializable {
	private static final long serialVersionUID = 6814409592238202749L;
	
	@GraphQLIgnore
	private MWeatherHourly hourly ;
	
}
