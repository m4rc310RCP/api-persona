package br.com.m4rc310.persona.api.dto.weather;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName="from")
public class DtoGeolocation {
	private BigDecimal lat;
	private BigDecimal lon;
}
