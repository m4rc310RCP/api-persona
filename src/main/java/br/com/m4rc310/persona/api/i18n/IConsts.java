package br.com.m4rc310.persona.api.i18n;


import br.com.m4rc310.gql.annotations.MConstants;

@MConstants
public interface IConsts {
//--------------------------------------------------
// @GraphQLQuery(name=AMONT$speed_wind, description=DESC$amont_speed_wind)
	public static final String AMONT$speed_wind             = "${amont.speed.wind}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$device_connected, description=DESC$amount_device_connected)
	public static final String AMOUNT$device_connected      = "${amount.device.connected}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$feels_like, description=DESC$amount_feels_like)
	public static final String AMOUNT$feels_like            = "${amount.feels.like}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$humidit, description=DESC$amount_humidit)
	public static final String AMOUNT$humidit               = "${amount.humidit}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$rain, description=DESC$amount_rain)
	public static final String AMOUNT$rain                  = "${amount.rain}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$rain_daily, description=DESC$amount_rain_daily)
	public static final String AMOUNT$rain_daily            = "${amount.rain.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$rain_in_hour, description=DESC$amount_rain_in_hour)
	public static final String AMOUNT$rain_in_hour          = "${amount.rain.in.hour}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$rain_minutely, description=DESC$amount_rain_minutely)
	public static final String AMOUNT$rain_minutely         = "${amount.rain.minutely}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$temp_daily, description=DESC$amount_temp_daily)
	public static final String AMOUNT$temp_daily            = "${amount.temp.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$temp_max_daily, description=DESC$amount_temp_max_daily)
	public static final String AMOUNT$temp_max_daily        = "${amount.temp.max.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$temp_min_daily, description=DESC$amount_temp_min_daily)
	public static final String AMOUNT$temp_min_daily        = "${amount.temp.min.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=AMOUNT$temperature, description=DESC$amount_temperature)
	public static final String AMOUNT$temperature           = "${amount.temperature}";
//--------------------------------------------------
// @GraphQLQuery(name=CODE$icon, description=DESC$code_icon)
	public static final String CODE$icon                    = "${code.icon}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$daily_weather, description=DESC$date_daily_weather)
	public static final String DATE$daily_weather           = "${date.daily.weather}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$end, description=DESC$date_end)
	public static final String DATE$end                     = "${date.end}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$hour_sunrise, description=DESC$date_hour_sunrise)
	public static final String DATE$hour_sunrise            = "${date.hour.sunrise}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$hour_sunset, description=DESC$date_hour_sunset)
	public static final String DATE$hour_sunset             = "${date.hour.sunset}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$init, description=DESC$date_init)
	public static final String DATE$init                    = "${date.init}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$update, description=DESC$date_update)
	public static final String DATE$update                  = "${date.update}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$weatcher, description=DESC$date_weatcher)
	public static final String DATE$weatcher                = "${date.weatcher}";
//--------------------------------------------------
// @GraphQLQuery(name=DATE$weather_minutely, description=DESC$date_weather_minutely)
	public static final String DATE$weather_minutely        = "${date.weather.minutely}";
	public static final String DESC$amont_speed_wind        = "${desc.amont.speed.wind}";
	public static final String DESC$amount_device_connected = "${desc.amount.device.connected}";
	public static final String DESC$amount_feels_like       = "${desc.amount.feels.like}";
	public static final String DESC$amount_humidit          = "${desc.amount.humidit}";
	public static final String DESC$amount_pop              = "${desc.amount.pop}";
	public static final String DESC$amount_rain             = "${desc.amount.rain}";
	public static final String DESC$amount_rain_daily       = "${desc.amount.rain.daily}";
	public static final String DESC$amount_rain_in_hour     = "${desc.amount.rain.in.hour}";
	public static final String DESC$amount_rain_minutely    = "${desc.amount.rain.minutely}";
	public static final String DESC$amount_temp_daily       = "${desc.amount.temp.daily}";
	public static final String DESC$amount_temp_max_daily   = "${desc.amount.temp.max.daily}";
	public static final String DESC$amount_temp_min_daily   = "${desc.amount.temp.min.daily}";
	public static final String DESC$amount_temperature      = "${desc.amount.temperature}";
	public static final String DESC$code_icon               = "${desc.code.icon}";
	public static final String DESC$date_daily_weather      = "${desc.date.daily.weather}";
	public static final String DESC$date_end                = "${desc.date.end}";
	public static final String DESC$date_hour_sunrise       = "${desc.date.hour.sunrise}";
	public static final String DESC$date_hour_sunset        = "${desc.date.hour.sunset}";
	public static final String DESC$date_init               = "${desc.date.init}";
	public static final String DESC$date_update             = "${desc.date.update}";
	public static final String DESC$date_weatcher           = "${desc.date.weatcher}";
	public static final String DESC$date_weather_minutely   = "${desc.date.weather.minutely}";
	public static final String DESC$describe_weather        = "${desc.describe.weather}";
	public static final String DESC$id_device               = "${desc.id.device}";
	public static final String DESC$info_daily_summary      = "${desc.info.daily.summary}";
	public static final String DESC$info_description        = "${desc.info.description}";
	public static final String DESC$info_event              = "${desc.info.event}";
	public static final String DESC$info_main               = "${desc.info.main}";
	public static final String DESC$info_weather            = "${desc.info.weather}";
	public static final String DESC$list_alerts             = "${desc.list.alerts}";
	public static final String DESC$list_hourly_weather     = "${desc.list.hourly.weather}";
	public static final String DESC$list_weather_daily      = "${desc.list.weather.daily}";
	public static final String DESC$list_weather_minutely   = "${desc.list.weather.minutely}";
	public static final String DESC$mudatation_send_email   = "${desc.mudatation.send.email}";
	public static final String DESC$name_city               = "${desc.name.city}";
	public static final String DESC$name_sender             = "${desc.name.sender}";
	public static final String DESC$name_state              = "${desc.name.state}";
	public static final String DESC$number_device_connected = "${desc.number.device.connected}";
	public static final String DESC$number_ip_client        = "${desc.number.ip.client}";
	public static final String DESC$number_lat              = "${desc.number.lat}";
	public static final String DESC$number_lon              = "${desc.number.lon}";
	public static final String DESC$number_services         = "${desc.number.services}";
	public static final String DESC$percent_clouds          = "${desc.percent.clouds}";
	public static final String DESC$percent_pop_daily       = "${desc.percent.pop.daily}";
	public static final String DESC$percent_pop_hour        = "${desc.percent.pop.hour}";
	public static final String DESC$query_device_connected  = "${desc.query.device.connected}";
	public static final String DESC$query_service_info      = "${desc.query.service.info}";
	public static final String DESC$query_test              = "${desc.query.test}";
	public static final String DESC$query_weather           = "${desc.query.weather}";
	public static final String DESC$subscription_heart_beat = "${desc.subscription.heart.beat}";
	public static final String DESC$subscription_weacher    = "${desc.subscription.weacher}";
	public static final String DESC$type_daily_weather      = "${desc.type.daily.weather}";
	public static final String DESC$type_heart_beat         = "${desc.type.heart.beat}";
	public static final String DESC$type_hourly_weather     = "${desc.type.hourly.weather}";
	public static final String DESC$type_minutely_weather   = "${desc.type.minutely.weather}";
	public static final String DESC$type_service_info       = "${desc.type.service.info}";
	public static final String DESC$type_weatcher_alert     = "${desc.type.weatcher.alert}";
//--------------------------------------------------
// @GraphQLQuery(name=ID$device, description=DESC$id_device)
	public static final String ID$device                    = "${id.device}";
//--------------------------------------------------
// @GraphQLQuery(name=INFO$daily_summary, description=DESC$info_daily_summary)
	public static final String INFO$daily_summary           = "${info.daily.summary}";
//--------------------------------------------------
// @GraphQLQuery(name=INFO$description, description=DESC$info_description)
	public static final String INFO$description             = "${info.description}";
//--------------------------------------------------
// @GraphQLQuery(name=INFO$event, description=DESC$info_event)
	public static final String INFO$event                   = "${info.event}";
//--------------------------------------------------
// @GraphQLQuery(name=INFO$main, description=DESC$info_main)
	public static final String INFO$main                    = "${info.main}";
//--------------------------------------------------
// @GraphQLQuery(name=INFO$weather, description=DESC$info_weather)
	public static final String INFO$weather                 = "${info.weather}";
//--------------------------------------------------
// @GraphQLQuery(name=LIST$alerts, description=DESC$list_alerts)
	public static final String LIST$alerts                  = "${list.alerts}";
//--------------------------------------------------
// @GraphQLQuery(name=LIST$hourly_weather, description=DESC$list_hourly_weather)
	public static final String LIST$hourly_weather          = "${list.hourly.weather}";
//--------------------------------------------------
// @GraphQLQuery(name=LIST$weather_daily, description=DESC$list_weather_daily)
	public static final String LIST$weather_daily           = "${list.weather.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=LIST$weather_minutely, description=DESC$list_weather_minutely)
	public static final String LIST$weather_minutely        = "${list.weather.minutely}";
//--------------------------------------------------
// @GraphQLQuery(name=MUDATATION$send_email, description=DESC$mudatation_send_email)
	public static final String MUDATATION$send_email        = "${mudatation.send.email}";
//--------------------------------------------------
// @GraphQLQuery(name=NAME$city, description=DESC$name_city)
	public static final String NAME$city                    = "${name.city}";
//--------------------------------------------------
// @GraphQLQuery(name=NAME$sender, description=DESC$name_sender)
	public static final String NAME$sender                  = "${name.sender}";
//--------------------------------------------------
// @GraphQLQuery(name=NAME$state, description=DESC$name_state)
	public static final String NAME$state                   = "${name.state}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$ip_client, description=DESC$number_ip_client)
	public static final String NUMBER$ip_client             = "${number.ip.client}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$lat, description=DESC$number_lat)
	public static final String NUMBER$lat                   = "${number.lat}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$lon, description=DESC$number_lon)
	public static final String NUMBER$lon                   = "${number.lon}";
//--------------------------------------------------
// @GraphQLQuery(name=NUMBER$services, description=DESC$number_services)
	public static final String NUMBER$services              = "${number.services}";
//--------------------------------------------------
// @GraphQLQuery(name=PERCENT$clouds, description=DESC$percent_clouds)
	public static final String PERCENT$clouds               = "${percent.clouds}";
//--------------------------------------------------
// @GraphQLQuery(name=PERCENT$pop_daily, description=DESC$percent_pop_daily)
	public static final String PERCENT$pop_daily            = "${percent.pop.daily}";
//--------------------------------------------------
// @GraphQLQuery(name=PERCENT$pop_hour, description=DESC$percent_pop_hour)
	public static final String PERCENT$pop_hour             = "${percent.pop.hour}";
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
// @GraphQLQuery(name=QUERY$weather, description=DESC$query_weather)
	public static final String QUERY$weather                = "${query.weather}";
//--------------------------------------------------
// @GraphQLSubscription(name=SUBSCRIPTION$heart_beat, description=DESC$subscription_heart_beat)
	public static final String SUBSCRIPTION$heart_beat      = "${subscription.heart.beat}";
//--------------------------------------------------
// @GraphQLSubscription(name=SUBSCRIPTION$weacher, description=DESC$subscription_weacher)
	public static final String SUBSCRIPTION$weacher         = "${subscription.weacher}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$daily_weather, description=DESC$type_daily_weather)
	public static final String TYPE$daily_weather           = "${type.daily.weather}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$heart_beat, description=DESC$type_heart_beat)
	public static final String TYPE$heart_beat              = "${type.heart.beat}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$hourly_weather, description=DESC$type_hourly_weather)
	public static final String TYPE$hourly_weather          = "${type.hourly.weather}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$minutely_weather, description=DESC$type_minutely_weather)
	public static final String TYPE$minutely_weather        = "${type.minutely.weather}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$service_info, description=DESC$type_service_info)
	public static final String TYPE$service_info            = "${type.service.info}";
//--------------------------------------------------
// @GraphQLType(name=TYPE$weatcher_alert, description=DESC$type_weatcher_alert)
	public static final String TYPE$weatcher_alert          = "${type.weatcher.alert}";
}

