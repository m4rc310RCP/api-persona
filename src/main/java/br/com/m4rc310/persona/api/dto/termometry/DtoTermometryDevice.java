package br.com.m4rc310.persona.api.dto.termometry;

import java.io.Serializable;

import br.com.m4rc310.persona.api.i18n.IConsts;
import io.leangen.graphql.annotations.types.GraphQLType;
import lombok.Data;

@Data
@GraphQLType(name = IConsts.TYPE$termometry_device, description = IConsts.DESC$type_termometry_device)
public class DtoTermometryDevice implements Serializable, IConsts {

	private static final long serialVersionUID = 1L;

//	@GraphQLIgnore
//	private TermometryDevice termometryDevice;
//
//	@GraphQLQuery(name=NUMBER$serial, description=DESC$number_serial)
//	private String getSerialNumber(@GraphQLContext DtoTermometryDevice device) {
//		try {
//			return termometryDevice.getSerialNumber();
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	@GraphQLQuery(name = NUMBER$unity, description = DESC$number_unity)
//	public Long getNumber(@GraphQLContext DtoTermometryDevice device) {
//		try {
//			return device.getTermometryDevice().getUnity().getNumber();			
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	@GraphQLQuery(name = NAME$unity, description = DESC$name_unity)
//	public String getName(@GraphQLContext DtoTermometryDevice device) {
//		try {
//			return device.getTermometryDevice().getUnity().getName();			
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	@GraphQLQuery(name = INDICATOR$registry, description = DESC$indicator_registry)
//	public Boolean getRegistry(@GraphQLContext DtoTermometryDevice device) {
//		return device.getTermometryDevice().getUnity() == null ? false : true;
//	}

	
	
	
}
