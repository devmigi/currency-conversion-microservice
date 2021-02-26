package com.mrigendra.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping(path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConversion(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		HashMap<String, String> params = new HashMap<>();
		params.put("from", from);
		params.put("to", to);
		
		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, params);
		
		CurrencyConversion currencyConversion = response.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, 
				currencyConversion.getConversionMultiple(), quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment());
			
	}
	
	
	@GetMapping(path = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateConversionFeign(
			@PathVariable String from, 
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
			
		CurrencyConversion currencyConversion = proxy.getCurrencyExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), from, to, 
				currencyConversion.getConversionMultiple(), quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), 
				currencyConversion.getEnvironment() + " using Feign");
			
	}
}
