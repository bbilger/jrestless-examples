package com.jrestless.aws.spring;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	private AtomicInteger counter = new AtomicInteger();

	public String greet() {
		return "Hello, #" + counter.getAndIncrement();
	}
}
