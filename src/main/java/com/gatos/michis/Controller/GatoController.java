package com.gatos.michis.Controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gatos.michis.Model.Gato;

@RestController
public class GatoController {

	private static final String template = "hola gato, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/saludo")
	public Gato greeting(@RequestParam(value = "name", defaultValue = "Michi World") String name) {
		return new Gato(counter.incrementAndGet(), String.format(template, name));
	}

}
