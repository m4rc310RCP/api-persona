package br.com.m4rc310.persona.api.db.models.termometry;

import java.io.Serializable;

import br.com.m4rc310.persona.api.db.models.unity.Unity;
import br.com.m4rc310.persona.api.i18n.IConsts;
import io.leangen.graphql.annotations.GraphQLIgnore;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = IConsts.TYPE$termometry_device)
@GraphQLType(name = IConsts.TYPE$termometry_device, description = IConsts.DESC$type_termometry_device)
public class TermometryDevice implements Serializable, IConsts {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = NUMBER$serial)
	@GraphQLQuery(name = NUMBER$serial, description = DESC$number_serial)
	private String serialNumber;

	@OneToOne(optional = true)
	@JoinColumn(name = NUMBER$unity)
	@GraphQLIgnore
	private Unity unity;

}
