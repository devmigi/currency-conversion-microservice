package com.mrigendra.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {
	
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to);
}
