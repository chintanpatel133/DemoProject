package com.javahungry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/")
	public String sayHello() {
		return "CICD flow with Jenkins and ArgoCD  !!!!!!";
	}

}
