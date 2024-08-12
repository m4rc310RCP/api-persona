package br.com.m4rc310.persona.api.dto.movto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = "${type.movto.fix}", description = "${desc.type.movto.fix}")
public class DtoMovtoFix implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@JsonAlias("cd_produto")
	@GraphQLQuery(name = "${code.product}", description = "${desc.code.product}")
	private Long code;
	
	@JsonAlias("nm_produto")
	@GraphQLQuery(name = "${name.product}", description = "${desc.name.product}")
	private Long name;
	
	
	
}
