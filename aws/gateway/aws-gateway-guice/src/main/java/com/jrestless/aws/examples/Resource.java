package com.jrestless.aws.examples;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class Resource {

	// if you want to use @com.google.inject.Inject, then check http://stackoverflow.com/a/39153195

	@javax.inject.Inject
	private HelloService greetingService;

	@javax.inject.Inject
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

	@Path("/hello-goodbye-javax")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloAndGoodbyeHk2() {
		return goodbyeService.greetAndGoodybeJavax();
	}

	@Path("/hello-goodbye-guice")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloAndGoodbyeGuice() {
		return goodbyeService.greetAndGoodybeGuice();
	}
}
