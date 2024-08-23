package br.com.m4rc310.persona.api.services;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import br.com.m4rc310.persona.api.dto.weather.DtoWeatcherData;
import br.com.m4rc310.weather.dto.MWeather;
import br.com.m4rc310.weather.services.MWeatherService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@GraphQLApi
@EnableScheduling
public class WeatherService extends MService {

  @Autowired
  private MWeatherService weatherService;
  
  @GraphQLQuery(name="${query.weather}")
  public DtoWeatcherData getWeather () throws Exception{
    BigDecimal lat = BigDecimal.valueOf(-24.046);
    BigDecimal lon = BigDecimal.valueOf(-52.3838);
    MWeather data = weatherService.getMWeather(lat, lon);
    return DtoWeatcherData.from(data);
  }

  @GraphQLQuery(name="${amount.temperature}")
  public BigDecimal getTemperature(@GraphQLContext DtoWeatcherData data){
    try {
      return  data.getWeather().getCurrent().getTemperature();
    } catch (Exception e) {
      return null;
    }
  }

  @GraphQLQuery(name="${percent.clouds}")
  public BigDecimal getCloudPersent(@GraphQLContext DtoWeatcherData data){
    try {
      return  data.getWeather().getCurrent().getClouds();
    } catch (Exception e) {
      return null;
    }
  }



}