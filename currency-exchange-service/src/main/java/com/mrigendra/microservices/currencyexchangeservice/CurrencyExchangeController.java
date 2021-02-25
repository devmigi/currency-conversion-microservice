package com.mrigendra.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyExchangeController {
	
	@GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {
		return new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(75));
	}

}
