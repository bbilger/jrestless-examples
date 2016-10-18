package com.jrestless.aws.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Path("/api")
@Controller
public class Resource {

	@Autowired
	private HelloService greetingService;

	@Autowired
	private GoodbyeService goodbyeService;

	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return greetingService.greet();
	}

	@Path("/goodbye")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayGoodbye() {
		return goodbyeService.goodbye();
	}
}
