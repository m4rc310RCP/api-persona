package br.com.m4rc310.persona.api.dto.weather;

import br.com.m4rc310.gql.location.dto.DtoGeolocation;
import br.com.m4rc310.weather.dto.MWeather;
import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.Data;

/**
 *
 * @author MLSilva
 */
@Data
public class DtoWeatcherData {
  @GraphQLIgnore
  private MWeather weather;
  
  @GraphQLIgnore
  private DtoGeolocation geolocation;
}
