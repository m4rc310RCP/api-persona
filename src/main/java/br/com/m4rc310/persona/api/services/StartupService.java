package br.com.m4rc310.persona.api.services;

import java.util.Date;
import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.DtoHeartBeat;
import br.com.m4rc310.persona.api.dto.DtoServiceInfo;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
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
	private DtoHeartBeat hb;

	@GraphQLQuery(name = "${query.test}")
	public String test() {
		return "OK";
	}

	@GraphQLSubscription(name = "${subscription.heart.beat}")
	public Publisher<DtoHeartBeat> heartBeat(@GraphQLArgument(name = "${id.device}") String sid) {
		if (hb == null) {
			hb = new DtoHeartBeat();
			hb.setNumberServices(0);
			hb.setDateUpdate(new Date());
		}
		return flux.publish(DtoHeartBeat.class, sid, hb);
	}

	@Scheduled(cron = "*/10 * * * * *")
	private void jobHeartBeat() throws Exception {
		Class<DtoHeartBeat> type = DtoHeartBeat.class;
		if (flux.getSizeRegistries(type) > 0) {
			
			if (flux.getSizeRegistries(DtoHeartBeat.class) > 0) {
				
//				for (String key : flux.getKeys(type)) {
					if (hb == null) {
						hb = new DtoHeartBeat();
						hb.setNumberServices(0);
					}
					hb.setNumberServices(hb.getNumberServices() + 1);
					hb.setDateUpdate(new Date());
////				
//					//System.out.println(key);
//					
//					flux.callPublish(DtoHeartBeat.class, key, hb);					
//				}
				flux.callPublish(DtoHeartBeat.class, hb);
//				
			}
//			
		}
		
		//log.info("-> " + flux.getKeys(DtoHeartBeat.class));
		

//			flux.callPublish(DtoHeartBeat.class, hb);
//		}		
		
	}

	@GraphQLQuery(name = "${query.service.info}", description = "${desc.query.service.info}")
	public DtoServiceInfo getInfo() {
		return new DtoServiceInfo();
	}

	@GraphQLQuery(name = "${amount.device.connected}", description = "${desc.amount.device.connected}")
	public Integer getConnectedDevices(@GraphQLContext DtoServiceInfo info) {
		return flux.getSizeRegistries(DtoHeartBeat.class);
	}

}
