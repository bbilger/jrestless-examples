package com.jrestless.aws.examples;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.jrestless.aws.gateway.GatewayFeature;
import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;

/**
 * The request handler as lambda function.
 *
 * @author Bjoern Bilger
 *
 */
public class RequestHandler extends GatewayRequestObjectHandler {
	public RequestHandler() {
		// bridge java.util.logging (used by Jersey) to SLF4J which will use log4j
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();

		ResourceConfig config = new ResourceConfig()
				.register(GatewayFeature.class)
				.packages("com.jrestless.aws.examples");
		init(config);
		start();
	}
}
