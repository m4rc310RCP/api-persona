package br.com.m4rc310.persona.api.services;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class StartupService extends MService{
	
	@GraphQLQuery(name = "${query.test}")
	public String test() {
		return "OK" ;
	}
}
