package br.com.m4rc310.persona.api.dto;

import java.io.Serializable;

import br.com.m4rc310.weather.dto.MWeatherDaily;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = "${type.daily.weather}")
public class DtoDailyWeather implements Serializable {
	private static final long serialVersionUID = 6814409592238202749L;
	
	@GraphQLIgnore
	private MWeatherDaily daily;
}
