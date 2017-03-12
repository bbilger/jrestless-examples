package com.jrestless.aws.examples;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RequestScoped
@Path("/api")
public class Resource {

	@Inject
	private HelloService greetingService;

	@Inject
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

	@Path("/hello-goodbye")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloAndGoodbye() {
		return goodbyeService.greetAndGoodbye();
	}
}
