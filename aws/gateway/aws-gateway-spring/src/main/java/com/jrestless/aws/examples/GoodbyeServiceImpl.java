package com.jrestless.aws.examples;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class GoodbyeServiceImpl implements GoodbyeService {

	private AtomicInteger counter = new AtomicInteger();

	@Override
	public String goodbye() {
		// this will always be "Goodbye, #0" since we use the prototype scope
		return "Goodbye, #" + counter.getAndIncrement();
	}
}
