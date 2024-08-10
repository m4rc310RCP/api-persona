package br.com.m4rc310.persona.api.services;

import org.reactivestreams.Publisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.DtoHeartBeat;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class StartupService extends MService{
	
	public static final String HEART_BEAT_KEY = "heart-beat-key";
	private DtoHeartBeat hb;
	
	
	@GraphQLQuery(name = "${query.test}")
	public String test() {
		return "OK" ;
	}
	
	@GraphQLSubscription(name = "${subscription.heart.beat}")
	public Publisher<DtoHeartBeat> heartBeat() {
		DtoHeartBeat defaulHeartBeat = new DtoHeartBeat();
		defaulHeartBeat.setNumberServices(0);
		return flux.publish(DtoHeartBeat.class, HEART_BEAT_KEY, defaulHeartBeat);
	}
	
	
	@Scheduled(cron = "*/10 * * * * *")
	private void jobHeartBeat() {
		if (flux.inPublish(DtoHeartBeat.class, HEART_BEAT_KEY)) {
			if (hb == null) {
				hb = new DtoHeartBeat();
				hb.setNumberServices(0);
			}
			hb.setNumberServices(hb.getNumberServices() + 1);
			try {
				flux.callPublish(HEART_BEAT_KEY, hb);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
}
