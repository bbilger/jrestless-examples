package com.jrestless.aws.examples;

import java.util.concurrent.atomic.AtomicInteger;

public class HelloService {

	private AtomicInteger counter = new AtomicInteger();

	public String greet() {
		return "Hello, #" + counter.getAndIncrement();
	}
}
