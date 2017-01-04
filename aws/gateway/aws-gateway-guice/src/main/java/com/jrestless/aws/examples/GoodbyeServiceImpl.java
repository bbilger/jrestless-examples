package com.jrestless.aws.examples;

import java.util.concurrent.atomic.AtomicInteger;

public class GoodbyeServiceImpl implements GoodbyeService {

	private AtomicInteger counter = new AtomicInteger();

	@javax.inject.Inject
	private HelloService helloServiceJavaxInject;

	@com.google.inject.Inject
	private HelloService helloServiceGuiceInject;

	@Override
	public String goodbye() {
		// this will always be "Goodbye, #0" since it's recreated
		return "Goodbye, #" + counter.getAndIncrement();
	}

	@Override
	public String greetAndGoodybeJavax() {
		return helloServiceJavaxInject.greet() + "\n" + goodbye();
	}

	@Override
	public String greetAndGoodybeGuice() {
		return helloServiceGuiceInject.greet() + "\n" + goodbye();
	}
}
