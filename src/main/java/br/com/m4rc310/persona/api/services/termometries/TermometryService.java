package br.com.m4rc310.persona.api.services.termometries;

import java.util.List;

import org.reactivestreams.Publisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.db.models.termometry.TermometryDevice;
import br.com.m4rc310.persona.api.db.models.unity.Unity;
import br.com.m4rc310.persona.api.services.MService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class TermometryService extends MService {
	
	public static final String KEY_UNLINKED_TERMOMETRIES = "key_unlinked_termometries";

	@GraphQLQuery(name = INDICATOR$registry, description = DESC$indicator_registry)
	public boolean inUnityLinked(@GraphQLContext TermometryDevice device) {
		try {
			return device.getUnity() != null;
		} catch (Exception e) {
			return false;
		}
	}

	@GraphQLMutation(name = MUTATION$termometry_device, description = DESC$mutation_termometry_device)
	public TermometryDevice storeDevice(@GraphQLArgument(name = FIELD$termometry_device) TermometryDevice device) {
		TermometryDevice local = termometryDeviceRepository.findById(device.getSerialNumber()).orElse(device);
		cloneAtoB(device, local);
		return termometryDeviceRepository.saveAndFlush(local);
	}

	@GraphQLMutation(name=MUTATION$link_termometry_unity, description=DESC$mutation_link_termometry_unity)
	public TermometryDevice linkTermometryDeviceUnity(
			@GraphQLArgument(name = NUMBER$serial, description = DESC$number_serial) String serial,
			@GraphQLArgument(name = NUMBER$unity, description = DESC$number_unity) Long numberUnity) throws Exception {
		TermometryDevice device = termometryDeviceRepository.findById(serial).orElseThrow();
		Unity unity = unityRepository.findById(numberUnity).orElseThrow();
		device.setUnity(unity);
		device =  termometryDeviceRepository.save(device);
		if (flux.inPublish(TermometryDevice.class, serial)) {
			flux.callPublish(serial, device);			
		}
		try {
			flux.callListPublish(TermometryDevice.class, KEY_UNLINKED_TERMOMETRIES, listUnlinkedTermometryDevice());
		} catch (Exception e) {
			
		}
		return device;
	}
	
	@GraphQLMutation(name=MUTATION$unlink_termometry_unity, description=DESC$mutation_unlink_termometry_unity)
	public TermometryDevice unlinkTermometryDeviceUnity(
			@GraphQLArgument(name = NUMBER$serial, description = DESC$number_serial) String serial) throws Exception {
		TermometryDevice device = termometryDeviceRepository.findById(serial).orElseThrow();
		device.setUnity(null);
		device =  termometryDeviceRepository.save(device);
		if (flux.inPublish(TermometryDevice.class, serial)) {
			flux.callPublish(serial, device);			
		}
		
		try {
			flux.callListPublish(TermometryDevice.class, KEY_UNLINKED_TERMOMETRIES, listUnlinkedTermometryDevice());
		} catch (Exception e) {
			
		}
		
		return device;
	}
	
	@GraphQLQuery(name=QUERY$unlinked_termometry_devices, description=DESC$query_unlinked_termometry_devices)
	public List<TermometryDevice> listUnlinkedTermometryDevice(){
		return termometryDeviceRepository.findAllByUnityIsNull();
	}
	
	
	@GraphQLQuery(name=QUERY$termometry_device, description=DESC$query_termometry_device)
	public TermometryDevice getTermometryDevice(@GraphQLArgument(name=NUMBER$serial, description=DESC$number_serial) String serial) {
		return termometryDeviceRepository.findById(serial).orElse(null);
	}
	
	@GraphQLQuery(name=NUMBER$unity, description=DESC$number_unity)
	public Long getUnityNumber(@GraphQLContext TermometryDevice device) {
		try {
			return device.getUnity().getNumber();
		} catch (Exception e) {
			return null;
		}
	}
	
	@GraphQLSubscription(name=SUBSCRIPTION$unlinked_termometries, description=DESC$subscription_unlinked_termometries)
	public Flux<List<TermometryDevice>> listUnlinkedTermometries() {
		return flux.publishList(TermometryDevice.class, KEY_UNLINKED_TERMOMETRIES, listUnlinkedTermometryDevice());
	}
	
	
	@GraphQLSubscription(name=SUBSCRIPTION$request_device_unity_link, description=DESC$subscription_request_device_unity_link)
	public Publisher<TermometryDevice> requestLinkDeviceUnity(@GraphQLArgument(name=NUMBER$serial, description=DESC$number_serial) String serial){
		return flux.publish(TermometryDevice.class, serial);
	}

}
