package br.com.m4rc310.persona.api.dto.weather;

import br.com.m4rc310.weather.dto.MWeather;
import io.leangen.graphql.annotations.GraphQLIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author MLSilva
 */
@Data
@AllArgsConstructor(staticName="from")
public class DtoWeatcherData {
  @GraphQLIgnore
  private MWeather weather;
}
