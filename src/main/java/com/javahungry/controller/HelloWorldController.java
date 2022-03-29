package com.javahungry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/")
	public String sayHello() {
		return "Webhook Integration with GitHub and Jenkins !!! -- Hello";
	}

}
