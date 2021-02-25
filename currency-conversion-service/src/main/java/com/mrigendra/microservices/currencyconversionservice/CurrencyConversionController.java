package com.mrigendra.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

	@GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConversion(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		
		return new CurrencyConversion(1000L, from, to, BigDecimal.valueOf(50), quantity, BigDecimal.ONE, "");
			
	}
}
