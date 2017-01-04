package com.jrestless.aws.examples;

import com.google.inject.AbstractModule;

public class GreetingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GoodbyeService.class).to(GoodbyeServiceImpl.class);
		bind(HelloService.class).asEagerSingleton();
	}
}
