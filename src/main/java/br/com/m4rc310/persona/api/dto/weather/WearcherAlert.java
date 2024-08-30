package br.com.m4rc310.persona.api.dto.weather;

import java.io.Serializable;

import br.com.m4rc310.weather.dto.MWeatherAlert;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = "${type.weatcher.alert}", description = "${desc.type.weatcher.alert}")
public class WearcherAlert implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@GraphQLIgnore
	private MWeatherAlert weatherAlert;
	

}
