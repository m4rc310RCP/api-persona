package br.com.m4rc310.persona.api.i18n;


import br.com.m4rc310.gql.annotations.MConstants;

@MConstants
public interface IConsts {
	public static final String DESC$number_services         = "${desc.number.services}";
	public static final String DESC$query_test              = "${desc.query.test}";
	public static final String DESC$subscription_heart_beat = "${desc.subscription.heart.beat}";
	public static final String DESC$type_heart_beat         = "${desc.type.heart.beat}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$services, description=DESC$number_services)
	public static final String NUMBER$services              = "${number.services}";
//--------------------------------------------------
// @GraphQLQuery(name=QUERY$test, description=DESC$query_test)
	public static final String QUERY$test                   = "${query.test}";
//--------------------------------------------------
// @GraphQLSubscription(name=SUBSCRIPTION$heart_beat, description=DESC$subscription_heart_beat)
	public static final String SUBSCRIPTION$heart_beat      = "${subscription.heart.beat}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$heart_beat, description=DESC$type_heart_beat)
	public static final String TYPE$heart_beat              = "${type.heart.beat}";
}

