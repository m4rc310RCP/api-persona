package br.com.m4rc310.persona.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.m4rc310.persona.api.i18n.IConsts;

public class MService extends br.com.m4rc310.gql.services.MService implements IConsts {
	protected static final String DATE_ISO_MASK = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	
	@Autowired
	protected EmailService emailService;
}
