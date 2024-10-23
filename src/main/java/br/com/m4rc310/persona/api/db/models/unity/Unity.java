package br.com.m4rc310.persona.api.db.models.unity;

import java.io.Serializable;

import br.com.m4rc310.persona.api.i18n.IConsts;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = IConsts.TYPE$unity)
@GraphQLType(name=IConsts.TYPE$unity, description=IConsts.DESC$type_unity)
public class Unity implements Serializable, IConsts {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = NUMBER$unity)
	@GraphQLQuery(name=NUMBER$unity, description=DESC$number_unity)
	private Long number;

	@Column(name = NAME$unity, length = 50)
	@GraphQLQuery(name=NAME$unity, description=DESC$name_unity)
	private String name;
}
