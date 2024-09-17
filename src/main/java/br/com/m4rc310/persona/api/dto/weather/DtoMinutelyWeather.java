package br.com.m4rc310.persona.api.dto.weather;

import java.io.Serializable;

import br.com.m4rc310.weather.dto.MWeatherMinutely;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name =  "${type.minutely.weather}")
public class DtoMinutelyWeather implements Serializable {
	private static final long serialVersionUID = 6814409592238202749L;
	
	@GraphQLIgnore
	private MWeatherMinutely minutely;

}
