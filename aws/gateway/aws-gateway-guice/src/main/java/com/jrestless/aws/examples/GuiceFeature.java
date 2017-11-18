package com.jrestless.aws.examples;

import javax.annotation.Priority;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.InjectionManagerProvider;
import org.glassfish.jersey.inject.hk2.DelayedHk2InjectionManager;
import org.glassfish.jersey.inject.hk2.ImmediateHk2InjectionManager;
import org.glassfish.jersey.internal.inject.InjectionManager;
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
		InjectionManager injectionManager = InjectionManagerProvider.getInjectionManager(context);
		ServiceLocator locator;
		if (injectionManager instanceof ImmediateHk2InjectionManager) {
			locator = ((ImmediateHk2InjectionManager) injectionManager).getServiceLocator();
		} else if (injectionManager instanceof DelayedHk2InjectionManager) {
			locator = ((DelayedHk2InjectionManager) injectionManager).getServiceLocator();
		} else {
			throw new IllegalStateException("expected an hk2 injection manager");
		}
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
		// register all your modules, here
		Injector injector = Guice.createInjector(new GreetingModule());
		GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(injector);
		return true;
	}

}
