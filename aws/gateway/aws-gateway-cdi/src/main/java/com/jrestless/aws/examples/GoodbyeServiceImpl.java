package com.jrestless.aws.examples;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;

import com.jrestless.aws.gateway.io.GatewayRequest;

@RequestScoped
public class GoodbyeServiceImpl implements GoodbyeService {

	private AtomicInteger counter = new AtomicInteger();

	@Inject
	private HelloService helloService;

	@Context
	private GatewayRequest gatewayRequest;

	@Override
	public String goodbye() {
		// this will always be "Goodbye, #0" since it's recreated
		return "Goodbye, #" + counter.getAndIncrement();
	}

	@Override
	public String greetAndGoodbye() {
		return helloService.greet() + "\n" + goodbye();
	}
}
