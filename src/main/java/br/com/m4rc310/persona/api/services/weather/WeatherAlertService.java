
package br.com.m4rc310.persona.api.services.weather;

import org.springframework.stereotype.Service;

import br.com.m4rc310.gql.mappers.annotations.MDate;
import br.com.m4rc310.persona.api.dto.weather.WearcherAlert;
import br.com.m4rc310.persona.api.services.MService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
public class WeatherAlertService extends MService {
	
	@GraphQLQuery(name = NAME$sender, description = DESC$name_sender)
	public String getAlertSender(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getSender();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = INFO$event, description = DESC$info_event)
	public String getAlertEvent(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getEvent();
		} catch (Exception e) {
			return null;
		}
	}

	@GraphQLQuery(name = INFO$description, description = DESC$info_description)
	public String getAlertDescription(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDescription();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = DATE_ISO_MASK)
	@GraphQLQuery(name = DATE$init, description = DESC$date_init)
	public Long getAlertDateStart(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDateStart();
		} catch (Exception e) {
			return null;
		}
	}

	@MDate(unixFormat = true, value = DATE_ISO_MASK)
	@GraphQLQuery(name = DATE$end, description = DESC$date_end)
	public Long getAlertDateEnd(@GraphQLContext WearcherAlert alert) {
		try {
			return alert.getWeatherAlert().getDateEnd();
		} catch (Exception e) {
			return null;
		}
	}

}
