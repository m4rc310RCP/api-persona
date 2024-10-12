package br.com.m4rc310.persona.api.services;

import java.util.Date;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.DtoHeartBeat;
import br.com.m4rc310.persona.api.dto.DtoServiceInfo;
import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.persona.api.services.weather.WeatherService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class StartupService extends MService {

	public static final String HEART_BEAT_KEY = "heart-beat-key";
	public static final String WEATCHER_KEY = "weatcher-key";
	private DtoHeartBeat hb;

	@Autowired
	private WeatherService weatherService;
	
	

	@GraphQLQuery(name = QUERY$test, description = DESC$query_test)
	public String test() {
		return "OK";
	}

	@GraphQLMutation(name = "${mudatation.send.email}")
	public String sendEmail() {
		sendEmail("marcelo.utfpr@me.com", WEATCHER_KEY, HEART_BEAT_KEY);
		return "OK";
	}

	@GraphQLSubscription(name = SUBSCRIPTION$heart_beat, description = DESC$subscription_heart_beat)
	public Publisher<DtoHeartBeat> heartBeat(
			@GraphQLArgument(name = ID$device, description = DESC$id_device) String sid) {
		if (hb == null) {
			hb = new DtoHeartBeat();
			hb.setNumberServices(0);
			hb.setDateUpdate(new Date());
		}
		return flux.publish(DtoHeartBeat.class, sid, hb);
	}

	@GraphQLSubscription(name = SUBSCRIPTION$weacher, description = DESC$subscription_weacher)
	public Publisher<DtoWeatcherData> subscribeWeatcher(
			@GraphQLArgument(name = ID$device, description = DESC$id_device) String deviceId) throws Exception {
		DtoWeatcherData weatherFrom = weatherService.getWeatherFrom();
		String sid = String.format("%s-%s", WEATCHER_KEY, deviceId);
		return flux.publish(DtoWeatcherData.class, sid, weatherFrom);
	}

	@Scheduled(cron = "*/10 * * * * *")
	private void jobHeartBeat() throws Exception {
		if (flux.getSizeRegistries(DtoHeartBeat.class) > 0) {
			if (hb == null) {
				hb = new DtoHeartBeat();
				hb.setNumberServices(0);
			}
			hb.setNumberServices(hb.getNumberServices() + 1);
			hb.setDateUpdate(new Date());
			flux.callPublish(DtoHeartBeat.class, hb);			
		}
		
		if (flux.getSizeRegistries(DtoWeatcherData.class) > 0) {
			DtoWeatcherData weather = weatherService.getWeatherFrom();
			flux.callPublish(DtoWeatcherData.class, weather);			
		}
	}

	@GraphQLQuery(name = QUERY$service_info, description = DESC$query_service_info)
	public DtoServiceInfo getInfo() {
		return new DtoServiceInfo();
	}

	@GraphQLQuery(name = AMOUNT$device_connected, description = DESC$amount_device_connected)
	public Integer getConnectedDevices(@GraphQLContext DtoServiceInfo info) {
		return flux.getSizeRegistries(DtoHeartBeat.class);
	}

	@GraphQLQuery(name = NUMBER$ip_client, description = DESC$number_ip_client)
	public String getClientIp(@GraphQLContext DtoServiceInfo info) {
		return flux.getIPClient();
	}

}
