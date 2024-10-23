package br.com.m4rc310.persona.api.services.unity;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.db.models.unity.Unity;
import br.com.m4rc310.persona.api.dto.unity.DtoUnity;
import br.com.m4rc310.persona.api.services.MService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class UnityService extends MService {

	@GraphQLMutation(name=MUTATION$unity, description=DESC$mutation_unity)
	public Unity storeUnity(@GraphQLArgument(name = FIELD$unity, description = DESC$field_unity) Unity unity) {
		Unity local = unityRepository.findById(unity.getNumber()).orElse(unity);
		cloneAtoB(local, unity);
		return unityRepository.save(unity);
	}
}
