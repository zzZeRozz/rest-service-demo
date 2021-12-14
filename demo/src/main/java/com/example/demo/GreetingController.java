package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	//define GreetingComponent variable without initialization
	private GreetingComponent g;
	
	//inject/create GreetingComponent object in the constructor
	@Autowired
	public GreetingController(GreetingComponent gc) {
		this.g=gc;
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) { 
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	//test GreetingComponent
	@GetMapping("/testgreeting")
	public ResponseEntity<String> getMessage(){
		return ResponseEntity.ok(g.getMessage());
	}
}
