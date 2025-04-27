package edu.cibertec.proyecto;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApplication {
	
	@RequestMapping("/")
	String home () {
		return "Proyecto Cibertec HIB v.01.1";
	}
	
	
}
