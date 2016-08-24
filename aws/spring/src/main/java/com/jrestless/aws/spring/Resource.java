package com.jrestless.aws.spring;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@Path("/")
@Controller
public class Resource {

	@Autowired
	private HelloService greetingService;

	@Autowired
	private GoodbyeService goodbyeService;

	@Path("hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	// API Gateway's swagger import won't accept a String response => set it to Object
	@ApiOperation(value = "hello", response = Object.class)
	public String sayHello() {
		return greetingService.greet();
	}

	@Path("goodbye")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	// API Gateway's swagger import won't accept a String response => set it to Object
	@ApiOperation(value = "goodbye", response = Object.class)
	public String sayGoodbye() {
		return goodbyeService.goodbye();
	}
}
