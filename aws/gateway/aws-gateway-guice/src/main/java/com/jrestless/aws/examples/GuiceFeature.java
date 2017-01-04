package com.jrestless.aws.examples;

import javax.annotation.Priority;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This feature allows injection of Guice services into HK2/Jersey (and Guice)
 * services.
 */
@Priority(1)
public class GuiceFeature implements Feature {

	@Override
	public boolean configure(FeatureContext context) {
		ServiceLocator locator = ServiceLocatorProvider.getServiceLocator(context);
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
		// register all your modules, here
		Injector injector = Guice.createInjector(new GreetingModule());
		GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(injector);
		return true;
	}

}
