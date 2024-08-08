package br.com.m4rc310.persona.api.dto;

import java.io.Serializable;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = "${type.heart.beat}")
public class DtoHeartBeat implements Serializable {
	private static final long serialVersionUID = 6814409592238202749L;
	
	@GraphQLQuery(name = "${number.services}")
	private Integer numberServices;
}
