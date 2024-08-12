package br.com.m4rc310.persona.api.i18n;


import br.com.m4rc310.gql.annotations.MConstants;

@MConstants
public interface IConsts {
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$device_connected, description=DESC$amount_device_connected)
	public static final String AMOUNT$device_connected      = "${amount.device.connected}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$update, description=DESC$date_update)
	public static final String DATE$update                  = "${date.update}";
	public static final String DESC$amount_device_connected = "${desc.amount.device.connected}";
	public static final String DESC$date_update             = "${desc.date.update}";
	public static final String DESC$id_device               = "${desc.id.device}";
	public static final String DESC$number_device_connected = "${desc.number.device.connected}";
	public static final String DESC$number_services         = "${desc.number.services}";
	public static final String DESC$query_device_connected  = "${desc.query.device.connected}";
	public static final String DESC$query_service_info      = "${desc.query.service.info}";
	public static final String DESC$query_test              = "${desc.query.test}";
	public static final String DESC$subscription_heart_beat = "${desc.subscription.heart.beat}";
	public static final String DESC$type_heart_beat         = "${desc.type.heart.beat}";
	public static final String DESC$type_service_info       = "${desc.type.service.info}";
//--------------------------------------------------
// @GraphQLQuery(name=ID$device, description=DESC$id_device)
	public static final String ID$device                    = "${id.device}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$services, description=DESC$number_services)
	public static final String NUMBER$services              = "${number.services}";
//--------------------------------------------------
// @GraphQLQuery(name=QUERY$device_connected, description=DESC$query_device_connected)
	public static final String QUERY$device_connected       = "${query.device.connected}";
//--------------------------------------------------
// @GraphQLQuery(name=QUERY$service_info, description=DESC$query_service_info)
	public static final String QUERY$service_info           = "${query.service.info}";
//--------------------------------------------------
// @GraphQLQuery(name=QUERY$test, description=DESC$query_test)
	public static final String QUERY$test                   = "${query.test}";
//--------------------------------------------------
// @GraphQLSubscription(name=SUBSCRIPTION$heart_beat, description=DESC$subscription_heart_beat)
	public static final String SUBSCRIPTION$heart_beat      = "${subscription.heart.beat}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$heart_beat, description=DESC$type_heart_beat)
	public static final String TYPE$heart_beat              = "${type.heart.beat}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$service_info, description=DESC$type_service_info)
	public static final String TYPE$service_info            = "${type.service.info}";
}

