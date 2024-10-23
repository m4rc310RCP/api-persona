package br.com.m4rc310.persona.api.dto.unity;

import br.com.m4rc310.persona.api.i18n.IConsts;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = IConsts.TYPE$unity, description = IConsts.DESC$type_unity)
public class DtoUnity implements IConsts {

	@GraphQLQuery(name = NUMBER$unity, description = DESC$number_unity)
	private Long number;

	@GraphQLQuery(name = NAME$unity, description = DESC$name_unity)
	private String name;
}
